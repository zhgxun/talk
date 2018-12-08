package com.github.zhgxun.talk.common.processor.impl;

import com.github.zhgxun.talk.common.processor.LoginProcessor;
import com.github.zhgxun.talk.common.util.HttpUtil;
import com.github.zhgxun.talk.config.WeiboConfig;
import com.github.zhgxun.talk.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

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
        String url = "https://api.weibo.com/oauth2/access_token";
        Map<String, Object> map = new HashMap<>();
        map.put("client_id", "3461295618");
        map.put("client_secret", "4d41bb55bf61c7e92567619fef6a16e2");
        map.put("grant_type", "authorization_code");
        map.put("code", "2ec9317e4291bb66b1fafc6586db3c5b");
        map.put("redirect_uri", "https://zhgxun.github.io");
        // {"access_token":"2.00Fl_WIGgGPPmD2fa5c74ae3fARRwC","remind_in":"157679999","expires_in":157679999,"uid":"5622710131","isRealName":"false"}
        try (HttpUtil httpUtil = new HttpUtil()) {
            String content = httpUtil.doPost(url, map);
            System.out.println(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
