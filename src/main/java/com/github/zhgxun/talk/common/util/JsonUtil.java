package com.github.zhgxun.talk.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * json工具
 */
public class JsonUtil {

    private static Gson GSON;

    static {
        GSON = new GsonBuilder().disableHtmlEscaping().create();
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
}
