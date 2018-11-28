package com.github.zhgxun.talk.common;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;
import com.github.zhgxun.talk.common.exception.SpeechException;
import com.github.zhgxun.talk.config.Constant;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 百度语音合成工具
 */
@Slf4j
public class AipSpeechUtil {

    private static final AipSpeech speech = new AipSpeech(Constant.APP_ID, Constant.APP_KEY, Constant.SECRET_KEY);

    private AipSpeechUtil() {
    }

    public static AipSpeech getSpeech() {
        return speech;
    }

    /**
     * 文本片段合成语音
     *
     * @param path    合成语音mp3文件后的存放路径, 包含扩展名
     * @param text    待合成语音的文本文件, 要求编码为 utf-8
     * @param options 可选参数, 详细为
     *                HashMap<String, Object> options = new HashMap<String, Object>();
     *                options.put("spd", "5"); String 语速, 取值0-9, 默认为5中语速
     *                options.put("pit", "5"); String 音调, 取值0-9, 默认为5中语调
     *                options.put("vol", "4"); String 音量, 取值0-15, 默认为5中音量
     *                options.put("per", "4"); String 发音人选择,
     *                0为女声, 1为男声, 3为情感合成-度逍遥, 4为情感合成-度丫丫, 默认为普通女
     */
    private static void fragment(
            String path, String text, HashMap<String, Object> options) {
        TtsResponse response = getSpeech().synthesis(text, Constant.LANG, Constant.CTP, options);
        byte[] data = response.getData();
        if (data != null) {
            try {
                Util.writeBytesToFileSystem(data, path);
            } catch (IOException e) {
                log.error("", e);
                throw new SpeechException(e.getMessage());
            }
        }
    }

    /**
     * 分散文本并进行语音合成
     *
     * @param path    语音合成目标文件
     * @param text    待生成语音的文本
     * @param options 可选设置
     * @param combine 是否进行片段语音文件合成一个文件
     * @return 语音合成后的目标文件
     */
    public static List<String> scatter(
            String path, String text, HashMap<String, Object> options, boolean combine) {
        List<String> target = new ArrayList<>();

        // 1. 打散合成语音
        List<String> texts = TextUtil.analysis(text);

        // 2. 片段语音合成
        for (String s : texts) {
            fragment(path, s, options);
            target.add(path);
        }

        // 3. 是否合并片段文件
        if (combine) {

        }

        // 4. 将合并后片段返回, 可以是完整合成后的文件路径, 也可能是片段组合, 根据传入的动作进行回环操作
        return target;
    }

    public static void main(String[] args) {
        String text = "Java是由Sun Microsystems公司于1995年5月推出的Java面向对象程序设计语言和Java平台的总称。由James Gosling和同事们共同研发，并在1995年正式推出。\n" +
                "\n" +
                "Java分为三个体系：\n" +
                "\n" +
                "    JavaSE（J2SE）（Java2 Platform Standard Edition，java平台标准版）\n" +
                "    JavaEE(J2EE)(Java 2 Platform,Enterprise Edition，java平台企业版)\n" +
                "    JavaME(J2ME)(Java 2 Platform Micro Edition，java平台微型版)。\n" +
                "\n" +
                "2005年6月，JavaOne大会召开，SUN公司公开Java SE 6。此时，Java的各种版本已经更名以取消其中的数字\"2\"：J2EE更名为Java EE, J2SE更名为Java SE，J2ME更名为Java ME。 ";
        String path = "log/test.mp3";
        scatter(path, text, null, true);
    }
}
