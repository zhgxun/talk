package com.github.zhgxun.translate.youdao;

import com.github.zhgxun.util.Http;
import com.github.zhgxun.util.Md5;

import java.util.HashMap;
import java.util.Map;

/**
 * 有道翻译API
 *
 * @link http://ai.youdao.com/docs/doc-trans-api.s#p01
 */
public class Api {

    /**
     * 有道翻译服务地址
     */
    private static final String HOST = "http://openapi.youdao.com/api";

    /**
     * APP ID
     */
    private static final String APP_ID = "0351f6c84398af01";

    /**
     * 密钥
     */
    private static final String SECURITY_KEY = "JxDLvGG64fh5ggCqXkXdERUSZRhfig8j";

    public static String result(String query, String from, String to) {
        Map<String, String> params = buildParams(query, from, to);
        return Http.post(HOST, params);
    }

    /**
     * 构造参数
     *
     * @param query 待翻译的内容, 一个字符串
     * @param from  翻译的原始语言简写
     * @param to    翻译的目标语言
     * @return 构造后的参数
     */
    private static Map<String, String> buildParams(String query, String from, String to) {
        String salt = String.valueOf(System.currentTimeMillis());
        String sign = Md5.md5(APP_ID + query + salt + SECURITY_KEY);

        Map<String, String> params = new HashMap<>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);
        params.put("sign", sign);
        params.put("salt", salt);
        params.put("appKey", APP_ID);

        return params;
    }

}
