package com.github.zhgxun.translate.baidu;

import java.util.HashMap;
import java.util.Map;

/**
 * 百度翻译API
 *
 * @link http://api.fanyi.baidu.com/api/trans/product/index
 */
public class Api {

    /**
     * 百度翻译服务地址
     */
    private static final String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";

    /**
     * APP ID
     */
    private static final String APPID = "20180316000136280";

    /**
     * 密钥
     */
    private static final String SECURITY_KEY = "b6NDaPt4l2xb4bk4x4tU";

    public Api() {
    }

    /**
     * 获取翻译结果
     *
     * @param query 待翻译的目标字符串
     * @param from  待翻译文本的原始语言
     * @param to    待翻译文本的目标语言
     * @return 翻译结果
     */
    public static String result(String query, String from, String to) {
        Map<String, String> params = buildParams(query, from, to);
        return Get.get(TRANS_API_HOST, params);
    }

    /**
     * 构造参数并加密
     *
     * @param query 待翻译的内容, 一个字符串
     * @param from  翻译的原始语言简写
     * @param to    翻译的目标语言
     * @return 加密后的构造参数
     */
    private static Map<String, String> buildParams(String query, String from, String to) {
        Map<String, String> params = new HashMap<>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);

        params.put("appid", APPID);

        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);

        // 签名
        String src = APPID + query + salt + SECURITY_KEY; // 加密前的原文
        params.put("sign", MD5.md5(src));

        return params;
    }
}