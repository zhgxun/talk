package com.github.zhgxun.talk.dao;

import com.github.zhgxun.talk.entity.BookEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookDao {

    int add(BookEntity entity);

    BookEntity findOne(@Param("id") int id, @Param("categoryId") int categoryId, @Param("title") String title);

    List<BookEntity> findAny(@Param("categoryId") int categoryId, @Param("title") String title, @Param("author") String author, @Param("nickName") String nickName);

    int update(@Param("id") int id, @Param("url") String url, @Param("description") String description, @Param("playCount") int playCount);

    int delete(@Param("id") int id);
}
