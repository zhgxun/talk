package com.github.zhgxun.talk.service;

import com.github.zhgxun.talk.common.enums.UserRole;
import com.github.zhgxun.talk.common.enums.UserType;

public interface UserService {

    String accessUrl(UserType type);

    String code(UserType type, String code);

    int add(String nickName, UserRole role, UserType type);
}
