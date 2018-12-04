package com.github.zhgxun.talk.entity;

import lombok.Data;

@Data
public class UserEntity extends BaseEntity {
    private String nickName;
    private int type;
}
