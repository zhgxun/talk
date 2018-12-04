package com.github.zhgxun.talk.entity;

import lombok.Data;

@Data
public class CategoryEntity extends BaseEntity {
    private int parentId;
    private String name;
}
