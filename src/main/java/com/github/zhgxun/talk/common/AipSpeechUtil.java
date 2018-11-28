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
     * @param origin  语音合成原始的目标文件
     * @param dest    语音合成期望的目标文件名
     * @param text    待生成语音的文本
     * @param options 可选设置
     * @param combine 是否进行片段语音文件合成一个文件
     * @return 语音合成后的目标文件
     */
    public static List<String> scatter(
            String origin, String dest, String text, HashMap<String, Object> options, boolean combine) throws IOException {
        List<String> target = new ArrayList<>();

        // 1. 打散合成语音
        List<String> texts = TextUtil.analysis(text);

        // 2. 片段语音合成
        int i = 1;
        for (String s : texts) {
            // 合成到合并目录中, 需要指定书本或者文章作为父目录, 便于该目录下的文件都可以合并为一个目标文件
            String fileName = String.format("%s/audio_%s.%s", origin, i++, Constant.AUDIO_EXT);
            FileUtil.createFile(fileName);
            fragment(fileName, s, options);
            target.add(origin);
        }

        // 3. 是否合并片段文件
        if (combine) {
            // 调用系统命令直接合成即可
            String exec = String.format("cat %s/audio_*.%s >> %s", origin, Constant.AUDIO_EXT, dest);
            // 注意命令执行方式书写形式
            String[] cmd = {"/bin/sh", "-c", exec};
            Runtime.getRuntime().exec(cmd);
        }

        // 4. 将合并后片段返回, 可以是完整合成后的文件路径, 也可能是片段组合, 根据传入的动作进行回环操作
        return target;
    }

    public static void main(String[] args) throws IOException {
        String text = "芙蓉楼送辛渐\n" +
                "\n" +
                "作者：王昌龄\n" +
                "\n" +
                "寒雨连江夜入吴，平明送客楚山孤。\n" +
                "洛阳亲友如相问，一片冰心在玉壶。";
        String dest = String.format("%s/article/article.mp3", Constant.DONE_DIR);
        FileUtil.deleteFolder(dest);
        FileUtil.createFile(dest);
        String origin = String.format("%s/article", Constant.MERGE_DIR);
        FileUtil.deleteFolder(origin);
        scatter(origin, dest, text, null, true);
    }
}
