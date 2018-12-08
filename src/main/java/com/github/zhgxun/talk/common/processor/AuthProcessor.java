package com.github.zhgxun.talk.common.processor;

import com.github.zhgxun.talk.common.exception.AuthException;
import com.github.zhgxun.talk.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登陆认证处理器
 */
public interface AuthProcessor {
    UserEntity auth(HttpServletRequest request, HttpServletResponse response) throws AuthException;
}
