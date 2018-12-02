package com.github.zhgxun.talk.common.util;

import com.github.zhgxun.talk.common.exception.LengthException;
import com.github.zhgxun.talk.common.processor.impl.LineProcessor;
import com.github.zhgxun.talk.config.Constant;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TextUtil {

    /**
     * 文本分析和拆解为有效的片段
     *
     * @param text 原始文本
     * @return 拆解后的文本片段
     */
    public static List<String> analysis(String text) {
        List<String> texts = new ArrayList<>();
        String lineText = new LineProcessor().process(text);
        if (lineText.length() <= Constant.MAX_TEXT_LENGTH) {
            texts.add(lineText);
            return texts;
        }

        split(texts, lineText);

        return texts;
    }

    private static void split(List<String> target, String text) {
        int len = 0;
        String has = "";

        String[] texts = text.split(Constant.DEFAULT_REPLACE);
        int count = texts.length;
        int i = 1;
        for (String t : texts) {
            int length = t.length();
            if (length > Constant.MAX_TEXT_LENGTH) {
                log.error("text: {} too long", t);
                throw new LengthException("单行文本片段长度超过最大限制, 功能不完整");
            }
            if (length >= Constant.MIDDLE_TEXT_LENGTH) {
                target.add(t);
            } else {
                len += length + 1;
                if (len > Constant.MAX_TEXT_LENGTH) {
                    target.add(has);
                    len = 0;
                    has = "";
                }
                has = has.concat(t + Constant.DEFAULT_REPLACE);
            }
            if (i++ >= count) {
                if (has.length() > 0) {
                    target.add(has);
                    has = "";
                }
            }
        }
    }
}
