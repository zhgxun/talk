package com.github.zhgxun.talk.manager;

import com.github.zhgxun.talk.entity.CategoryEntity;

import java.util.List;

public interface CategoryManager {

    CategoryEntity add(CategoryEntity entity);

    CategoryEntity findOne(int id);

    List<CategoryEntity> any();
}
