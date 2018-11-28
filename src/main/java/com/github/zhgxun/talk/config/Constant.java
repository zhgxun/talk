package com.github.zhgxun.talk.config;

/**
 * 系统常量配置
 */
public class Constant {
    /**
     * 百度AI开放平台: http://ai.baidu.com/
     * 语音合成文档: https://ai.baidu.com/docs#/TTS-Online-Java-SDK/top
     */
    public static final String URL = "http://tsn.baidu.com/text2audio";
    public static final String APP_ID = "14965244";
    public static final String APP_KEY = "xdDa5ph3fSZY7jIQmCMnEz2G";
    public static final String SECRET_KEY = "pGatO6LxFTFN43Z19HcanQCvuwPMmjnH";
    public static final int QPS = 100;
    public static final String LANG = "zh";
    public static final int CTP = 1;

    // 语音合成后的存放路径, 不可丢失, 相对路径需要存入数据库, 外部直接通过路径访问文件即可
    public static final String BASE_DIR = "";
    // 语音合成后的文件扩展名
    public static final String SPEECH_EXT = "mp3";

    /**
     * 由于中文关系, 需要人为添加句号(。)来作为一句话的结束, 不严格区分是逗号还是句号, 目的是让语音合成后有一定的暂停
     */
    // 原始文本中的空格, 可能存在多个
    // 匹配任何空白字符，包括空格、制表符、换页符等。与 [ \f\n\r\t\v] 等效。
    public static final String DEFAULT_SPACE = "\\s+";
    public static final String DEFAULT_CTLF = "\r\n|\n| |   |　　";
    // 将所有相邻的空格或者无效字符替换后为一个中文句号, 作为一个断句的标识
    public static final String DEFAULT_REPLACE = "。";
    // 一段文本的最大长度, 按UTF-8每个中文占3个字节算, 最大不得超过1024个字节, 因此一次请求可以接纳长度为 341*3=1023 341个中文
    // 包括汉字和标点符号等
    public static final int MAX_TEXT_LENGTH = 341;
    // 文本中断长度, 一行文本超过该长度即无需在进行拼接
    public static final int MIDDLE_TEXT_LENGTH = 300;
}
