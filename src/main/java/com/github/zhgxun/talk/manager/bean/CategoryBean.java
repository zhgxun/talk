package com.github.zhgxun.talk.manager.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryBean {
    private int id;
    private int parentId;
    private int level;
    private String name;
    List<CategoryBean> children = new ArrayList<>();

    public CategoryBean() {

    }

    public CategoryBean(int id, int parentId, int level, String name) {
        this.id = id;
        this.parentId = parentId;
        this.level = level;
        this.name = name;
    }
}
