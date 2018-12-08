package com.github.zhgxun.talk.common.processor.impl;

import com.github.zhgxun.talk.common.processor.LoginProcessor;
import com.github.zhgxun.talk.entity.UserEntity;

/**
 * QQ登陆
 */
public class QqLoginProcessor implements LoginProcessor {

    @Override
    public String accessUrl(String url) {
        return null;
    }

    @Override
    public String getCode(String code) {
        return null;
    }

    @Override
    public String accessToken(String code) {
        return null;
    }

    @Override
    public UserEntity userInfo(String accessToken) {
        return null;
    }
}
