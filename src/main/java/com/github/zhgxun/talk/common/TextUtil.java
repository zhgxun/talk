package com.github.zhgxun.talk.common;

import com.github.zhgxun.talk.common.exception.LengthException;
import com.github.zhgxun.talk.common.processor.LineProcessor;
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
        // 1. 替换文本中的特殊字符组合为一个长文本
        String lineText = new LineProcessor().process(text);
        // 2. 根据目标分隔符拆解文本为指定长度
        // 2.1 长度没有超出范围, 直接作为一个子元素操作
        if (lineText.length() <= Constant.MAX_TEXT_LENGTH) {
            texts.add(lineText);
            return texts;
        }

        // 2.2 长度需要分解
        return split(lineText);
    }

    private static List<String> split(String text) {
        List<String> target = new ArrayList<>();

        // 记录已有文本长度
        int len = 0;
        // 保留已遍历文本内容
        String has = "";

        String[] texts = text.split(Constant.DEFAULT_REPLACE);
        int count = texts.length;
        // 遍历拼接成为一个相对合适的长度
        int i = 1;
        for (String t : texts) {
            int length = t.length();
            // 如果一个分片的长度本身超过长度, 则需要继续拆解
            if (length > Constant.MAX_TEXT_LENGTH) {
                // 暂时抛出一个异常告诉开发者, 确实有足够长的文本片段出现, 该流程需要处理, 在处理这块
                // 暂时不处理
                log.error("text: {} too long", t);
                throw new LengthException("单行文本片段长度超过最大限制, 功能不完整");
            }
            if (length >= Constant.MIDDLE_TEXT_LENGTH) {
                target.add(t);
            } else {
                // 多个文本片段拼接为一个文本片段
                len += length + 1;
                // 如果拼接上当前片段后大于最大长度, 则说明上一次长度已经足够合适
                if (len > Constant.MAX_TEXT_LENGTH) {
                    // 将上一次的字符串转存到目标位置
                    target.add(has);
                    // 长度和字符串临时存储清空, 重新开始记录
                    len = 0;
                    has = "";
                }
                has = has.concat(t + Constant.DEFAULT_REPLACE);
            }
            // 最后一次遍历即是当前为最后一段文本, 直接保存即可
            if (i++ >= count) {
                if (has.length() > 0) {
                    target.add(has);
                    has = "";
                }
            }
        }

        return target;
    }

    public static void main(String[] args) {
        // 1. 原始字符文本
        String text = "飞机上，一位工程师和一位程序员坐在一起。程序员问工程师是否乐意和他一起玩一种有趣的游戏。"
                + "工程师想睡觉，于是他很有礼貌地拒绝了，转身要睡觉。程序员坚持要玩并解释说这是一个非常有趣的游"
                + "戏：\"我问你一个问题，如果你不知道答案，我付你5美元。然后你问我一个问题，如果我答不上来，我付"
                + "你5美元。\"然而，工程师又很有礼貌地拒绝了，又要去睡觉。　　程序员这时有些着急了，他说：\"好吧，"
                + "如果你不知道答案，你付5美元；如果我不知道答案，我付50美元。\"果然，这的确起了作用，工程师答应了。"
                + "程序员就问：\"从地球到月球有多远？\"工程师一句话也没有说，给了程序员5美元。　　现在轮到工程师了，"
                + "他问程序员：\"什么上山时有三条腿，下山却有四条腿？\"程序员很吃惊地看着工程师，拿出他的便携式电脑，"
                + "查找里面的资料，过了半个小时，他叫醒工程师并给了工程师50美元。工程师很礼貌地接过钱又要去睡觉。程序员"
                + "有些恼怒，问：\"那么答案是什么呢？\"工程师什么也没有说，掏出钱包，拿出5美元给程序员，转身就去睡觉了。";
        // 2. 替换所有换行符
        for (String s : analysis(text)) {
            log.info("{}", s);
        }
    }
}
