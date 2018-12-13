package com.github.zhgxun.talk.entity;

import lombok.Data;

@Data
public class ItemEntity extends BaseEntity {
    private int bookId;
    private String author;
    private String name;
    private String duration;
    private String format;
    private String url;
    private String description;
}
