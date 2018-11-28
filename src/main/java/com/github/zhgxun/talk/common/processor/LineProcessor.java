package com.github.zhgxun.talk.common.processor;

import com.github.zhgxun.talk.config.Constant;

import java.util.regex.Pattern;

/**
 * 处理文本, 替换文本中的空格, 回车, 换行和制表符为空格占位符
 */
public class LineProcessor implements Processor {

    @Override
    public String process(String text) {
        Pattern space = Pattern.compile(String.format("%s|%s", Constant.DEFAULT_SPACE, Constant.DEFAULT_CTLF));
        return space.matcher(text).replaceAll(Constant.DEFAULT_REPLACE);
    }
}
