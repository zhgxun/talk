package com.github.zhgxun.user.lib;

import com.github.zhgxun.lib.UserInfoLib;
import com.github.zhgxun.lib.UserLib;
import com.github.zhgxun.models.User;
import com.github.zhgxun.models.UserInfo;
import com.github.zhgxun.util.Aes;

import java.sql.SQLException;

/**
 * 用户信息处理
 */
public class UsersLib {

    /**
     * 添加微信用户
     *
     * @param encrypt 小程序返回的加密用户信息
     * @param iv      小程序返回的初始化向量
     * @return 是否添加用户信息
     * @throws SQLException exception
     */
    public static boolean add(String encrypt, String iv) throws SQLException {
        // 1. 解密用户数据
        String decrypt = Aes.decrypt(UserLib.sessionKey, encrypt, iv);
        if (decrypt == null) {
            return false;
        }
        User user = UserLib.getUser(decrypt);

        // 2. 用户是否存在
        if (UserInfoLib.haveOne(user.getOpenId())) {
            return true;
        }

        // 3. 添加用户
        UserInfo userInfo = new UserInfo();
        userInfo.setOpenId(user.getOpenId());
        userInfo.setNickname(user.getNickName());
        userInfo.setAvatarUrl(user.getAvatarUrl());

        return UserInfoLib.add(userInfo) >= 1;
    }

    /**
     * 通过开放平台标识获取用户信息
     *
     * @param openId 开放平台标识
     * @return {@link UserInfo} 普通用户对象
     * @throws SQLException exception
     */
    public static UserInfo info(String openId) throws SQLException {
        return UserInfoLib.getUserInfo(openId);
    }
}
