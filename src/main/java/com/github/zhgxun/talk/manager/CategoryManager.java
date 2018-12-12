package com.github.zhgxun.talk.manager;

import com.github.zhgxun.talk.entity.CategoryEntity;
import com.github.zhgxun.talk.manager.bean.CategoryBean;

import java.util.List;

public interface CategoryManager {

    CategoryEntity add(int parentId, String name, int level);

    CategoryBean findOne(int id, String name);

    List<CategoryBean> any();
}
