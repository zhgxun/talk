package com.github.zhgxun.talk.common.plugin;

import com.github.zhgxun.talk.common.util.DateUtil;
import com.github.zhgxun.talk.common.util.UserUtil;
import com.github.zhgxun.talk.entity.BaseEntity;
import com.github.zhgxun.talk.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Properties;

/**
 * 全局拦截数据库创建和更新
 * <p>
 * Signature 对应 Invocation 构造器, type 为 Invocation.Object, method 为 Invocation.Method, args 为 Invocation.Object[]
 * method 对应的 update 包括了最常用的 insert/update/delete 三种操作, 因此 update 本身无法直接判断sql为何种执行过程
 * args 包含了其余多有的操作信息, 按数组进行存储, 不同的拦截方式有不同的参数顺序, 具体看type接口的方法签名, 然后根据签名解析, 参见官网
 *
 * @link http://www.mybatis.org/mybatis-3/zh/configuration.html#plugins 插件
 * <p>
 * MappedStatement 包括了SQL具体操作类型, 需要通过该类型判断当前sql执行过程
 */
@Component
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
@Slf4j
public class NormalPlugin implements Interceptor {

    @Override
    @SuppressWarnings("unchecked")
    public Object intercept(Invocation invocation) throws Throwable {
        // 根据签名指定的args顺序获取具体的实现类
        // 1. 获取MappedStatement实例, 并获取当前SQL命令类型
        MappedStatement ms = (MappedStatement) invocation.getArgs()[0];
        SqlCommandType commandType = ms.getSqlCommandType();

        // 2. 获取当前正在被操作的类, 有可能是Java Bean, 也可能是普通的操作对象, 比如普通的参数传递
        // 普通参数, 即是 @Param 包装或者原始 Map 对象, 普通参数会被 Mybatis 包装成 Map 对象
        // 即是 org.apache.ibatis.binding.MapperMethod$ParamMap
        Object parameter = invocation.getArgs()[1];
        // 获取拦截器指定的方法类型, 通常需要拦截 update
        String methodName = invocation.getMethod().getName();
        log.info("NormalPlugin, methodName; {}, commandType: {}", methodName, commandType);

        // 3. 获取当前用户信息
        UserEntity userEntity = UserUtil.getCurrentUser();
        // 默认测试参数值
        int creator = 2, updater = 3;

        if (parameter instanceof BaseEntity) {
            // 4. 实体类
            BaseEntity entity = (BaseEntity) parameter;
            if (userEntity != null) {
                creator = entity.getCreator();
                updater = entity.getUpdater();
            }
            if (methodName.equals("update")) {
                if (commandType.equals(SqlCommandType.INSERT)) {
                    entity.setCreator(creator);
                    entity.setUpdater(updater);
                    entity.setCreateTime(DateUtil.getTimeStamp());
                    entity.setUpdateTime(DateUtil.getTimeStamp());
                } else if (commandType.equals(SqlCommandType.UPDATE)) {
                    entity.setUpdater(updater);
                    entity.setUpdateTime(DateUtil.getTimeStamp());
                }
            }
        } else if (parameter instanceof Map) {
            // 5. @Param 等包装类
            // 更新时指定某些字段的最新数据值
            if (commandType.equals(SqlCommandType.UPDATE)) {
                // 遍历参数类型, 检查目标参数值是否存在对象中, 该方式需要应用编写有一些统一的规范
                // 否则均统一为实体对象, 就免去该重复操作
                Map map = (Map) parameter;
                if (map.containsKey("creator")) {
                    map.put("creator", creator);
                }
                if (map.containsKey("updateTime")) {
                    map.put("updateTime", DateUtil.getTimeStamp());
                }
            }
        }
        // 6. 均不是需要被拦截的类型, 不做操作
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
