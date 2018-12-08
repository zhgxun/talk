package com.github.zhgxun.talk.common.processor.impl;

import com.github.zhgxun.talk.common.processor.LoginProcessor;
import com.github.zhgxun.talk.config.WeiboConfig;
import com.github.zhgxun.talk.entity.UserEntity;
import org.springframework.stereotype.Component;

/**
 * 微博登陆
 */
@Component
public class WeiboLoginProcessor implements LoginProcessor {

    @Override
    public String accessUrl(String url) {
        return String.format("%s/%s?client_id=%s&response_type=code&redirect_uri=%s&type=WEIBO",
                WeiboConfig.BASE_URL, WeiboConfig.AUTH, WeiboConfig.CLIENT_ID, url);
    }

    @Override
    public String getCode(String code) {
        return code;
    }

    @Override
    public String accessToken(String code) {
        return String.format("%s/%s?client_id=%s&client_secret=%s&grant_type=authorization_code&code=%s",
                WeiboConfig.BASE_URL, WeiboConfig.TOKEN, WeiboConfig.CLIENT_ID, WeiboConfig.CLIENT_SECERT, code);
    }

    @Override
    public UserEntity userInfo(String accessToken) {
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new WeiboLoginProcessor().accessToken("e1d6ce7b004e61a7fc51e8da3bf3d4bd"));
    }
}
