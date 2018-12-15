package com.github.zhgxun.talk.entity;

import lombok.Data;

@Data
public class OauthEntity extends BaseEntity {
    private int userId;
    private String oauthName;
    private String oauthAccessToken;
    private int oauthExpires;
}
