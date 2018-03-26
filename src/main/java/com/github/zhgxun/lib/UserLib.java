package com.github.zhgxun.lib;

import com.github.zhgxun.models.User;
import com.github.zhgxun.models.WaterMark;

import com.github.zhgxun.util.Aes;
import org.json.JSONObject;

/**
 * 用户信息
 */
public class UserLib {

    /**
     * 小程序安全加密key
     */
    public final static String sessionKey = "3a776ead15a4023f1f454c16cf068073";

    /**
     * 生成解密后的用户对象
     *
     * @param json 解密后的json字符串
     * @return {@link User } 微信用户信息对象
     */
    public static User getUser(String json) {
        User user = new User();

        JSONObject object = new JSONObject(json);

        String openId = object.getString("openId");
        user.setOpenId(openId);

        String nickName = object.getString("nickName");
        user.setNickName(nickName);

        int gender = object.getInt("gender");
        user.setGender(gender);

        String language = object.getString("language");
        user.setLanguage(language);

        String city = object.getString("city");
        user.setCity(city);

        String province = object.getString("province");
        user.setProvince(province);

        String country = object.getString("country");
        user.setCountry(country);

        String avatarUrl = object.getString("avatarUrl");
        user.setAvatarUrl(avatarUrl);

        String unionId = object.getString("unionId");
        user.setUnionId(unionId);

        WaterMark waterMark = new WaterMark();

        JSONObject mark = object.getJSONObject("watermark");

        int timestamp = mark.getInt("timestamp");
        waterMark.setTimestamp(timestamp);

        String appId = mark.getString("appid");
        waterMark.setAppId(appId);

        user.setWaterMark(waterMark);

        return user;
    }

    /**
     * 获取用户开放平台标识
     *
     * @param encrypt 加密数据
     * @param iv      初始向量
     * @return 开放平台标识
     */
    public static String getOpenId(String encrypt, String iv) {
        String decrypt = Aes.decrypt(sessionKey, encrypt, iv);
        if (decrypt == null) {
            return null;
        }
        User user = getUser(decrypt);

        return user.getOpenId();
    }
}
