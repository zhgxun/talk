package com.github.zhgxun.test.controller;

import com.github.zhgxun.translate.baidu.Api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class TranslateTest {

    @RequestMapping("/baidu")
    public com.github.zhgxun.test.models.Translate baidu(@RequestParam(value = "content", defaultValue = "你好") String content) {
        String result = Api.result(content, "zh", "en");
        return new com.github.zhgxun.test.models.Translate(new AtomicLong().incrementAndGet(), String.format("翻译结果: %s", result));
    }

    @RequestMapping("/youdao")
    public void youdao(@RequestParam(value = "content", defaultValue = "teacher") String content) {
        String result = com.github.zhgxun.translate.youdao.Api.result(content, "EN", "zh-CHS");
        System.out.println(result);
    }
}
