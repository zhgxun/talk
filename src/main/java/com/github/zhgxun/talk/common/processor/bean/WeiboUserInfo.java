package com.github.zhgxun.talk.common.processor.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class WeiboUserInfo {
    // 用户标识
    private String id;
    // 用户昵称
    private String name;
    // 用户描述
    private String description;
    // 用户头像地址
    @SerializedName("profile_image_url")
    private String profileImageUrl;
    // 用户微博主页
    @SerializedName("profile_url")
    private String profileUrl;
}
