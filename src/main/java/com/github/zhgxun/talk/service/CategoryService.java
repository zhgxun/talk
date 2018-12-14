package com.github.zhgxun.talk.service;

import com.github.zhgxun.talk.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {

    CategoryEntity add(CategoryEntity entity);

    CategoryEntity findOne(int id, String name);

    List<CategoryEntity> findAny(int id, int parentId, int level);

    int delete(int id);
}
