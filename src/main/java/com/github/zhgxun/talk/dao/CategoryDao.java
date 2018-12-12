package com.github.zhgxun.talk.dao;

import com.github.zhgxun.talk.entity.CategoryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryDao {

    CategoryEntity add(CategoryEntity entity);

    CategoryEntity findOne(@Param("id") int id);

    List<CategoryEntity> any();
}
