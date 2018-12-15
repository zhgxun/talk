package com.github.zhgxun.talk.common.processor.impl;

import com.github.zhgxun.talk.common.processor.LoginProcessor;
import com.github.zhgxun.talk.common.processor.bean.ThirdUserPart;
import com.github.zhgxun.talk.common.processor.bean.WeixinAccessToken;

/**
 * 微信登陆
 */
public class WeixinLoginProcessor implements LoginProcessor<WeixinAccessToken> {

    @Override
    public String accessUrl(String url) {
        return null;
    }

    @Override
    public String getCode(String code) {
        return null;
    }

    @Override
    public WeixinAccessToken accessToken(String code) {
        return null;
    }

    @Override
    public ThirdUserPart userInfo(WeixinAccessToken accessToken) {
        return null;
    }
}
