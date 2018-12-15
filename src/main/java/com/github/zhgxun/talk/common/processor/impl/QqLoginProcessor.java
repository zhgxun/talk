package com.github.zhgxun.talk.common.processor.impl;

import com.github.zhgxun.talk.common.processor.LoginProcessor;
import com.github.zhgxun.talk.common.processor.bean.QqAccessToken;
import com.github.zhgxun.talk.common.processor.bean.ThirdUserPart;

/**
 * QQ登陆
 */
public class QqLoginProcessor implements LoginProcessor<QqAccessToken> {

    @Override
    public String accessUrl(String url) {
        return null;
    }

    @Override
    public String getCode(String code) {
        return null;
    }

    @Override
    public QqAccessToken accessToken(String code) {
        return null;
    }

    @Override
    public ThirdUserPart userInfo(QqAccessToken accessToken) {
        return null;
    }
}
