package com.github.zhgxun.talk.common.processor.bean;

import lombok.Data;

/**
 * 第三方用户信息统一
 */
@Data
public class ThirdUserPart {
    private String name;
    private String home;
    private String url;
    private String accessToken;
    private int remindIn;
    private int expiresIn;
    private String oauthId;
}
