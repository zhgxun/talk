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

    int update(UserEntity entity);

    /**
     * 查找用户
     *
     * @param id      用户标识
     * @param oauthId 用户三方平台标识
     * @param type    用户三方平台
     * @return 用户信息
     */
    UserEntity findOne(@Param("id") int id, @Param("oauthId") String oauthId, @Param("type") int type);

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
