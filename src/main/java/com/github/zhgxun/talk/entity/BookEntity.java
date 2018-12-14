package com.github.zhgxun.talk.entity;

import lombok.Data;

@Data
public class BookEntity extends BaseEntity {
    private int categoryId;
    private String title;
    private String author;
    private String nickName;
    private String url;
    private String description;
    private int playCount;
}
