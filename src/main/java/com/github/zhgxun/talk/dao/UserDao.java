package com.github.zhgxun.talk.dao;

import com.github.zhgxun.talk.common.enums.UserRole;
import com.github.zhgxun.talk.common.enums.UserType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {

    int add(@Param("nickName") String nickName,
            @Param("role") UserRole role,
            @Param("type") UserType type,
            @Param("creator") int creator,
            @Param("updater") int updater,
            @Param("create_time") int createTime,
            @Param("update_time") int updateTime);
}
