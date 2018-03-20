package com.github.zhgxun.translate.youdao;

import com.github.zhgxun.util.Md5;

import java.util.HashMap;
import java.util.Map;

public class Api {

    /**
     * 有道翻译服务地址
     */
    private static final String HOST = "http://openapi.youdao.com/api";

    /**
     * APP ID
     */
    private static final String APP_ID = "";

    /**
     * 密钥
     */
    private static final String SECURITY_KEY = "";

    public static String result(String query, String from, String to) {
        String salt = String.valueOf(System.currentTimeMillis());
        String sign = Md5.md5(SECURITY_KEY + query + salt + APP_ID);
        Map<String, String> params = new HashMap<>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);
        params.put("sign", sign);
        params.put("salt", salt);
        params.put("appKey", SECURITY_KEY);
        return Http.post(HOST, params);
    }


}
