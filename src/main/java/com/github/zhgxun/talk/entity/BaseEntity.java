package com.github.zhgxun.talk.entity;

import lombok.Data;

@Data
class BaseEntity {
    private int id;
    private int creator;
    private int updater;
    private int createTime;
    private int updateTime;
}
