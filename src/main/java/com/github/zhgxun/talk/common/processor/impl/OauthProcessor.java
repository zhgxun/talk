package com.github.zhgxun.talk.common.processor.impl;

import com.github.zhgxun.talk.common.exception.AuthException;
import com.github.zhgxun.talk.common.processor.AuthProcessor;
import com.github.zhgxun.talk.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OauthProcessor implements AuthProcessor {

    @Override
    public UserEntity auth(HttpServletRequest request, HttpServletResponse response) throws AuthException {
        // 实现用户认证
        return null;
    }
}
