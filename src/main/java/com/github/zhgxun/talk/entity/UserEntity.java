package com.github.zhgxun.talk.entity;

import com.github.zhgxun.talk.common.enums.UserRole;
import com.github.zhgxun.talk.common.enums.UserType;
import lombok.Data;

@Data
public class UserEntity extends BaseEntity {
    private String nickName;
    private UserRole role;
    private UserType type;
}
