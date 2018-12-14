package com.github.zhgxun.talk.dao;

import com.github.zhgxun.talk.entity.ItemEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemDao {

    int add(ItemEntity entity);

    ItemEntity findOne(@Param("id") int id);

    List<ItemEntity> findAny(@Param("id") int id, @Param("bookId") int bookId, @Param("author") String author, @Param("name") String name);

    int delete(@Param("id") int id, @Param("bookId") int bookId);
}
