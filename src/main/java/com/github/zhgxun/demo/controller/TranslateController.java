package com.github.zhgxun.demo.controller;

import com.github.zhgxun.demo.models.Translate;
import com.github.zhgxun.translate.baidu.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class TranslateController {

    @RequestMapping("/translate")
    public Translate show(@RequestParam(value = "content", defaultValue = "你好") String content) {
        String result = Api.result(content, "zh", "en");
        return new Translate(new AtomicLong().incrementAndGet(), String.format("翻译结果: %s", result));
    }
}
