package com.github.zhgxun.words.lib;

import com.github.zhgxun.translate.youdao.Api;
import com.github.zhgxun.models.Word;
import com.github.zhgxun.words.util.JsonUtil;

/**
 * 处理翻译
 */
public class TranslateLib {

    /**
     * 处理翻译
     *
     * @param content 待翻译的单词
     * @return 翻译结果对象
     */
    public Word response(String content) {
        // @todo逻辑 如果单词已经存在缓存或者数据库中, 则无需再次请求接口进行翻译
        String result = Api.result(content, "EN", "zh-CHS");
        JsonUtil json = new JsonUtil();
        return json.parse(result);
    }
}
