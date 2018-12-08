package com.github.zhgxun.talk.common.processor;

import com.github.zhgxun.talk.entity.UserEntity;

/**
 * 用户登陆处理器
 */
public interface LoginProcessor {
    /**
     * 1. 跳转登陆授权页面
     *
     * @param url 需要第三方回调时附带的地址
     * @return 跳转第三方网站的登陆链接
     */
    String accessUrl(String url);

    /**
     * 2. 获取用户授权后的code
     *
     * @param code 返回的code
     * @return 用户授权后由第三方网站回调并获取code标识
     */
    String getCode(String code);

    /**
     * 3. 通过用户授权后返回的code获取access_token
     *
     * @param code 通过code标识获取第三方网站访问权限access token
     * @return 第三方提供的访问权限access token
     */
    String accessToken(String code);

    /**
     * 4. 查询用户基本信息
     *
     * @param accessToken 第三方网站提供的访问权限
     * @return 主动查询用户信息, 完成登陆
     */
    UserEntity userInfo(String accessToken);
}
