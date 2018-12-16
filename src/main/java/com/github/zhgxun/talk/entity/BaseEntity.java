package com.github.zhgxun.talk.entity;

import lombok.Data;

@Data
public class BaseEntity {
    private int id;
    private int creator;
    private int updater;
    private Long createTime;
    private Long updateTime;
}
