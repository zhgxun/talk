package com.github.zhgxun.talk.dao;

import com.github.zhgxun.talk.entity.OauthEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OauthDao {

    int add(OauthEntity entity);

    int delete(@Param("userId") int userId);
}
