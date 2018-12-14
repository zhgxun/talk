package com.github.zhgxun.talk.dao;

import com.github.zhgxun.talk.entity.CategoryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryDao {

    int add(CategoryEntity entity);

    CategoryEntity findOne(@Param("id") int id, @Param("name") String name);

    List<CategoryEntity> findAny(@Param("id") int id, @Param("parentId") int parentId, @Param("level") int level);

    int delete(@Param("id") int id);
}
