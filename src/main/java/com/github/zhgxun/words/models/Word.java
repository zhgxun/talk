package com.github.zhgxun.words.models;

/**
 * 单词管理表模板, 具体的单词按书本来编排, 一本书一个表
 *
 * @link http://ai.youdao.com/docs/doc-trans-api.s#p03
 */
public class Word {

    private Integer id;

    /**
     * 源语言
     */
    private String query;

    /**
     * 翻译结果
     */
    private String translation;

    /**
     * 默认音标, 默认是英式音标
     */
    private String phonetic;

    /**
     * 美式音标
     */
    private String usPhonetic;

    /**
     * 英式音标
     */
    private String ukPhonetic;

    /**
     * 美式发音
     */
    private String usSpeech;

    /**
     * 英式发音
     */
    private String ukSpeech;

    /**
     * 基本释义
     */
    private String explains;

    /**
     * 词义
     */
    private String web;

    /**
     * 源语言和目标语言
     */
    private String l;

    /**
     * 词典deeplink
     */
    private String dict;

    /**
     * webdeeplink
     */
    private String webDict;

    /**
     * 翻译结果发音地址
     */
    private String tSpeakUrl;

    /**
     * 源语言发音地址
     */
    private String speakUrl;

    /**
     * 书中原文例句
     */
    private String example;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getPhonetic() {
        return phonetic;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }

    public String getUsPhonetic() {
        return usPhonetic;
    }

    public void setUsPhonetic(String usPhonetic) {
        this.usPhonetic = usPhonetic;
    }

    public String getUkPhonetic() {
        return ukPhonetic;
    }

    public void setUkPhonetic(String ukPhonetic) {
        this.ukPhonetic = ukPhonetic;
    }

    public String getUsSpeech() {
        return usSpeech;
    }

    public void setUsSpeech(String usSpeech) {
        this.usSpeech = usSpeech;
    }

    public String getUkSpeech() {
        return ukSpeech;
    }

    public void setUkSpeech(String ukSpeech) {
        this.ukSpeech = ukSpeech;
    }

    public String getExplains() {
        return explains;
    }

    public void setExplains(String explains) {
        this.explains = explains;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public String getDict() {
        return dict;
    }

    public void setDict(String dict) {
        this.dict = dict;
    }

    public String getWebDict() {
        return webDict;
    }

    public void setWebDict(String webDict) {
        this.webDict = webDict;
    }

    public String gettSpeakUrl() {
        return tSpeakUrl;
    }

    public void settSpeakUrl(String tSpeakUrl) {
        this.tSpeakUrl = tSpeakUrl;
    }

    public String getSpeakUrl() {
        return speakUrl;
    }

    public void setSpeakUrl(String speakUrl) {
        this.speakUrl = speakUrl;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }
}
