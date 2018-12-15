package com.github.zhgxun.talk.common.processor.impl;

import com.github.zhgxun.talk.common.processor.LoginProcessor;
import com.github.zhgxun.talk.common.processor.bean.ThirdUserPart;
import com.github.zhgxun.talk.common.processor.bean.WeiboAccessToken;
import com.github.zhgxun.talk.common.processor.bean.WeiboUserInfo;
import com.github.zhgxun.talk.common.util.HttpUtil;
import com.github.zhgxun.talk.common.util.JsonUtil;
import com.github.zhgxun.talk.config.WeiboConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 微博登陆
 */
@Component
@Slf4j
public class WeiboLoginProcessor implements LoginProcessor<WeiboAccessToken> {

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
    public WeiboAccessToken accessToken(String code) {
        String host = String.format("%s/%s", WeiboConfig.BASE_URL, WeiboConfig.TOKEN);
        Map<String, Object> map = new HashMap<>();
        map.put("client_id", WeiboConfig.CLIENT_ID);
        map.put("client_secret", WeiboConfig.CLIENT_SECERT);
        map.put("grant_type", "authorization_code");
        map.put("code", code);
        map.put("redirect_uri", WeiboConfig.REDIRECT_URI);
        try (HttpUtil httpUtil = new HttpUtil()) {
            // {"access_token":"2.00Fl_WIGgGPPmD2fa5c74ae3fARRwC","remind_in":"157679999","expires_in":157679999,
            // "uid":"5622710131","isRealName":"false"}
            String token = httpUtil.doPost(host, map);
            log.info("Weibo accessToken: {}", token);
            return JsonUtil.fromJson(token, WeiboAccessToken.class);
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }

    @Override
    public ThirdUserPart userInfo(WeiboAccessToken accessToken) {
        String host = String.format("%s/%s?access_token=%s&uid=%s",
                WeiboConfig.BASE_URL, WeiboConfig.SHOW, accessToken.getAccessToken(), accessToken.getUid());
        try (HttpUtil httpUtil = new HttpUtil()) {
            String content = httpUtil.doGet(host);
            log.info("Weibo UserInfo: {}", content);
            WeiboUserInfo userInfo = JsonUtil.fromJson(content, WeiboUserInfo.class);
            ThirdUserPart part = new ThirdUserPart();
            part.setName(userInfo.getName());
            part.setHome(userInfo.getProfileUrl());
            part.setUrl(userInfo.getProfileImageUrl());
            part.setAccessToken(accessToken.getAccessToken());
            part.setRemindIn(accessToken.getRemindIn());
            part.setExpiresIn(accessToken.getExpiresIn());
            part.setOauthId(userInfo.getId());
            return part;
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }
}
