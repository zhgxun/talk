package com.github.zhgxun.lib;

import com.github.zhgxun.models.User;
import com.github.zhgxun.models.WaterMark;

import org.json.JSONObject;

/**
 * 用户信息
 */
public class UserLib {

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
}
