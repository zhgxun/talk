package com.github.zhgxun.talk.common.util;

import com.github.zhgxun.talk.common.processor.bean.WeiboAccessToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * json工具
 */
public class JsonUtil {

    private static Gson GSON;

    static {
        GSON = getGB().create();
    }

    private static GsonBuilder getGB() {
        return new GsonBuilder().disableHtmlEscaping();
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return GSON.fromJson(json, classOfT);
    }

    @SuppressWarnings("unchecked")
    public static <T> T fromJson(String json, Type type) {
        return (T) GSON.fromJson(json, type);
    }

    public static String toJson(Object src, Type typeOfSrc) {
        return GSON.toJson(src, typeOfSrc);
    }

    public static String toJson(Object o) {
        return GSON.toJson(o);
    }

    public static void main(String[] args) {
        String json = "{\"access_token\":\"2.00Fl_WIGgGPPmD2fa5c74ae3fARRwC\",\"remind_in\":\"157679999\",\"expires_in\":157679999,\"uid\":\"5622710131\",\"isRealName\":\"false\"}";
        System.out.println(fromJson(json, WeiboAccessToken.class));
    }
}
