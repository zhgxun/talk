package com.github.zhgxun.talk.config;

/**
 * 新浪微博相关配置
 */
public class WeiboConfig {
    // 新浪开放平台认证地址
    public static final String BASE_URL = "https://api.weibo.com";
    public static final String CLIENT_ID = "3461295618";
    public static final String CLIENT_SECERT = "4d41bb55bf61c7e92567619fef6a16e2";
    // 用户主动认证
    public static final String AUTH = "oauth2/authorize";
    // 获取认证后的code信, 并获取access_token信息
    public static final String TOKEN = "oauth2/access_token";
    // 通过access_token获取用户基本信息, 昵称头像等即可
    public static final String SHOW = "2/users/show.json";
    // 重定向, 即第三方回调授权地址
    public static final String REDIRECT_URI = "http://zhgxun.github.io/user/code?type=WEIBO";
    // 微博个人主页地址
    public static final String WEIBO_POST = "https://weibo.com";
}
