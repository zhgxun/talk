package com.github.zhgxun.talk.common.processor.impl;

import com.github.zhgxun.talk.common.processor.TextProcessor;

/**
 * 单行文本过长的处理
 */
public class LengthTextProcessor implements TextProcessor {

    @Override
    public String process(String text) {
        return text;
    }
}
