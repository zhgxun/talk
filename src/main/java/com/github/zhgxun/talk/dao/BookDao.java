package com.github.zhgxun.talk.dao;

import com.github.zhgxun.talk.entity.BookEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookDao {

    int add(BookEntity entity);

    BookEntity findOne(@Param("id") int id);

    List<BookEntity> any(@Param("title") String title, @Param("author") String author, @Param("nickName") String nickName);

    int update(@Param("url") String url, @Param("description") String description, @Param("playCount") int playCount);
}
