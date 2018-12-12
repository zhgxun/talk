package com.github.zhgxun.talk.dao;

import com.github.zhgxun.talk.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {

    /**
     * 新增用户
     *
     * @param entity 用户实体
     * @return 影响行数
     */
    int add(UserEntity entity);

    /**
     * 通过主键查找用户
     *
     * @param id 主键ID
     * @return 用户
     */
    UserEntity findOne(@Param("id") int id);

    /**
     * 根据主键或者昵称获取用户
     *
     * @param id       主键
     * @param nickName 昵称
     * @param type     平台类型
     * @return 用户列表
     */
    List<UserEntity> findAny(@Param("id") int id, @Param("nickName") String nickName, @Param("type") int type);

    int delete(@Param("id") int id);
}
