package com.github.zhgxun.user.controllers;

import com.github.zhgxun.models.UserInfo;
import com.github.zhgxun.user.lib.UsersLib;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 普通用户管理
 */
@RestController
public class Users {

    /**
     * 添加微信用户信息到小程序
     *
     * @param encrypt 小程序获取到的微信加密用户信息
     * @param iv      小程序获取到的微信加密初始化向量
     * @return boolean
     */
    @RequestMapping("/add")
    public boolean add(@RequestParam(value = "encrypt") String encrypt, @RequestParam(value = "iv") String iv) {
        try {
            return UsersLib.add(encrypt, iv);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 通过开放平台标识获取用户信息
     *
     * @param openId 开放平台标识
     * @return {@link UserInfo} 普通用户信息
     */
    @RequestMapping("/userinfo")
    public UserInfo userInfo(@RequestParam(value = "openId") String openId) {
        try {
            return UsersLib.info(openId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
