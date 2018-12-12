package com.github.zhgxun.talk.manager.impl;

import com.github.zhgxun.talk.common.exception.NormalException;
import com.github.zhgxun.talk.entity.CategoryEntity;
import com.github.zhgxun.talk.manager.CategoryManager;
import com.github.zhgxun.talk.manager.bean.CategoryBean;
import com.github.zhgxun.talk.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryManagerImpl implements CategoryManager {

    @Autowired
    private CategoryService categoryService;

    @Override
    public CategoryEntity add(int parentId, String name, int level) {
        if (categoryService.findOne(0, name) != null) {
            throw new NormalException("类目已经存在");
        }

        CategoryEntity entity = new CategoryEntity();
        entity.setParentId(parentId);
        entity.setName(name);
        entity.setLevel(level);
        categoryService.add(entity);
        return entity;
    }

    @Override
    public CategoryBean findOne(int id, String name) {
        CategoryEntity entity = categoryService.findOne(id, name);
        return depth(entity);
    }

    @Override
    public List<CategoryBean> any() {
        List<CategoryBean> beans = new ArrayList<>();
        // 1. 所有一级类目
        List<CategoryEntity> entities = categoryService.any(0, 0, 1);
        for (CategoryEntity entity : entities) {
            CategoryBean bean = new CategoryBean(
                    entity.getId(), entity.getParentId(), entity.getLevel(), entity.getName());
            // 2. 该一级类目下的所有二级类目
            List<CategoryEntity> entities1 = categoryService.any(0, entity.getId(), 2);
            for (CategoryEntity entity1 : entities1) {
                CategoryBean bean1 = new CategoryBean(
                        entity1.getId(), entity1.getParentId(), entity1.getLevel(), entity1.getName());
                bean.getChildren().add(bean1);
                // 3. 该二级类目下的所有三级类目
                List<CategoryEntity> entities2 = categoryService.any(0, entity1.getId(), 3);
                for (CategoryEntity entity2 : entities2) {
                    CategoryBean bean2 = new CategoryBean(
                            entity2.getId(), entity2.getParentId(), entity2.getLevel(), entity2.getName());
                    bean1.getChildren().add(bean2);
                }
            }
            beans.add(bean);
        }
        return beans;
    }

    private CategoryBean depth(CategoryEntity entity) {
        if (entity.getLevel() == 3) {
            CategoryEntity entity1 = categoryService.findOne(entity.getParentId(), null);
            CategoryEntity entity2 = categoryService.findOne(entity1.getParentId(), null);
            CategoryBean bean = new CategoryBean(
                    entity2.getId(), entity2.getParentId(), entity2.getLevel(), entity2.getName());
            bean.getChildren().add(new CategoryBean(
                    entity1.getId(), entity1.getParentId(), entity1.getLevel(), entity1.getName()));
            bean.getChildren().get(0).getChildren().add(new CategoryBean(
                    entity.getId(), entity.getParentId(), entity.getLevel(), entity.getName()));
            return bean;
        } else if (entity.getLevel() == 2) {
            CategoryEntity entity1 = categoryService.findOne(entity.getParentId(), null);
            CategoryBean bean = new CategoryBean(
                    entity1.getId(), entity1.getParentId(), entity1.getLevel(), entity1.getName());
            bean.getChildren().add(new CategoryBean(
                    entity.getId(), entity.getParentId(), entity.getLevel(), entity.getName()));
            return bean;
        } else {
            return new CategoryBean(
                    entity.getId(), entity.getParentId(), entity.getLevel(), entity.getName());
        }
    }
}
