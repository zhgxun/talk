package com.github.zhgxun.words.controllers;

import com.github.zhgxun.words.lib.TranslateLib;
import com.github.zhgxun.models.Word;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试用例
 */
@RestController
public class Translate {

    /**
     * 获取翻译结果, 默认只提供英文翻译为中文的服务
     *
     * @param content 需要翻译的单词
     * @return 翻译结果json格式值
     */
    @RequestMapping("/translate")
    public Word translate(@RequestParam(value = "content") String content) {
        TranslateLib translateLib = new TranslateLib();
        return translateLib.response(content);
    }
}
