package com.github.zhgxun.words.controllers;

import com.github.zhgxun.words.lib.TranslateLib;
import com.github.zhgxun.words.models.Word;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Translate {

    /**
     * 获取翻译结果, 默认只提供英文翻译为中文的服务
     *
     * @param content 需要翻译的单词
     * @return 翻译结果json格式值
     */
    @RequestMapping("/translate")
    public Word translate(String content) {
        TranslateLib translateLib = new TranslateLib();
        return translateLib.response(content);
    }
}
