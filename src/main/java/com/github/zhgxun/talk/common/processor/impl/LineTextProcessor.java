package com.github.zhgxun.talk.common.processor.impl;

import com.github.zhgxun.talk.common.processor.TextProcessor;
import com.github.zhgxun.talk.config.SpeechConfig;

import java.util.regex.Pattern;

/**
 * 处理文本, 替换文本中的空格, 回车, 换行和制表符为空格占位符
 */
public class LineTextProcessor implements TextProcessor {

    @Override
    public String process(String text) {
        Pattern space = Pattern.compile(String.format("%s|%s", SpeechConfig.DEFAULT_SPACE, SpeechConfig.DEFAULT_CTLF));
        return space.matcher(text).replaceAll(SpeechConfig.DEFAULT_REPLACE);
    }
}
