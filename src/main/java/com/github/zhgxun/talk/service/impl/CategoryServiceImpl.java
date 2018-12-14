package com.github.zhgxun.talk.service.impl;

import com.github.zhgxun.talk.common.exception.NormalException;
import com.github.zhgxun.talk.dao.CategoryDao;
import com.github.zhgxun.talk.entity.CategoryEntity;
import com.github.zhgxun.talk.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public CategoryEntity add(CategoryEntity entity) {
        categoryDao.add(entity);
        return entity;
    }

    @Override
    public CategoryEntity findOne(int id, String name) {
        return categoryDao.findOne(id, name);
    }

    @Override
    public List<CategoryEntity> findAny(int id, int parentId, int level) {
        return categoryDao.findAny(id, parentId, level);
    }

    @Override
    public int delete(int id) {
        CategoryEntity entity = categoryDao.findOne(id, null);
        if (entity == null) {
            throw new NormalException("类目不存在");
        }
        return categoryDao.delete(entity.getId());
    }
}
