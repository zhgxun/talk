package com.github.zhgxun.words.util;

import com.github.zhgxun.models.Word;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 解析字符串为单词对象
 */
public class JsonUtil {

    /**
     * 解析有道翻译结果
     *
     * @param json 有道翻译响应结果字符串
     * @return word对象
     */
    public Word parse(String json) {
        Word word = new Word();

        // 将字符串转为json对象
        JSONObject object = new JSONObject(json);
        // 源语言
        String query = object.getString("query");
        word.setQuery(query);

        // 翻译结果
        JSONArray translation = object.getJSONArray("translation");
        StringBuilder translations = new StringBuilder();
        for (int i = 0; i < translation.length(); i++) {
            String s = (String) translation.get(i);
            translations.append(s);
        }
        word.setTranslation(translations.toString());

        // 英文查词的basic字段中又包含以下字段, 中文查词basic只包含explains字段, 基本词典,查词时才有
        JSONObject basic = object.getJSONObject("basic");
        word.setPhonetic(basic.getString("phonetic"));
        word.setUkPhonetic(basic.getString("uk-phonetic"));
        word.setUsPhonetic(basic.getString("us-phonetic"));
        word.setUkSpeech(basic.getString("uk-speech"));
        word.setUsSpeech(basic.getString("us-speech"));
        word.setExplains(basic.getJSONArray("explains").toString());

        // 词义web, 网络释义, 该结果不一定存在
        JSONArray web = object.getJSONArray("web");
        word.setWeb(web.toString());

        // 源语言和目标语言
        String l = object.getString("l");
        word.setL(l);

        // 词典deeplink, 查询语种为支持语言时存在
        JSONObject dict = object.getJSONObject("dict");
        word.setDict(dict.getString("url"));

        // webdeeplink, 查询语种为支持语言时存在
        JSONObject webDict = object.getJSONObject("webdict");
        word.setWebDict(webDict.getString("url"));

        // 翻译结果发音地址, 翻译成功一定存在
        String tSpeakUrl = object.getString("tSpeakUrl");
        word.settSpeakUrl(tSpeakUrl);

        // 源语言发音地址, 翻译成功一定存在
        String speakUrl = object.getString("speakUrl");
        word.setSpeakUrl(speakUrl);

        return word;
    }
}
