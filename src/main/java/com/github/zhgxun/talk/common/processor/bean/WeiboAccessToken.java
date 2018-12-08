package com.github.zhgxun.talk.common.processor.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class WeiboAccessToken {
    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("remind_in")
    private int remindIn;
    @SerializedName("expires_in")
    private int expiresIn;
    private String uid;
    private boolean isRealName;
}
