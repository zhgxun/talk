package com.github.zhgxun.talk.common.processor.impl;


import com.github.zhgxun.talk.common.processor.Processor;

/**
 * 单行文本过长的处理
 */
public class LengthProcessor implements Processor {

    @Override
    public String process(String text) {
        return text;
    }
}
