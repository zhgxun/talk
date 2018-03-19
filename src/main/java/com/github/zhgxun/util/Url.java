package com.github.zhgxun.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * URL相关处理
 */
public class Url {

    /**
     * 根据api地址和参数生成请求URL
     *
     * @param url    请求地址
     * @param params 参数
     * @return 拼接后的地址
     */
    public static String query(String url, Map<String, String> params) {
        if (params == null) {
            return url;
        }

        StringBuilder builder = new StringBuilder(url);
        if (url.contains("?")) {
            builder.append("&");
        } else {
            builder.append("?");
        }

        int i = 0;
        for (String key : params.keySet()) {
            String value = params.get(key);
            if (value == null) {
                continue;
            }

            if (i != 0) {
                builder.append('&');
            }

            builder.append(key);
            builder.append('=');
            builder.append(encode(value));

            i++;
        }

        return builder.toString();
    }

    /**
     * 对输入的字符串进行URL编码, 即转换为%20这种形式
     *
     * @param input 原文
     * @return URL编码. 如果编码失败, 则返回原文
     */
    private static String encode(String input) {
        if (input == null) {
            return "";
        }

        try {
            return URLEncoder.encode(input, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return input;
    }
}
