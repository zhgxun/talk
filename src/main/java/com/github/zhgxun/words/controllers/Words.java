package com.github.zhgxun.words.controllers;

import com.github.zhgxun.models.Word;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 单词管理
 */
@RequestMapping("/words")
@RestController
public class Words {

    /**
     * 按倒序分页获取单词列表
     *
     * @param number 图书编号
     * @param query  待搜索的单词
     * @param limit  每页获取单词数量
     * @return {@link Word} 单词列表
     */
    @RequestMapping("/words")
    public List<Word> words(@RequestParam(value = "number") int number, @RequestParam(value = "query") String query, @RequestParam(value = "limit") int limit) {
        List<Word> words = new ArrayList<>();
        return words;
    }

    /**
     * 获取一个现有单词明细
     *
     * @param id 单词ID
     * @return {@link Word} 单词对象
     */
    @RequestMapping("/info")
    public Word info(@RequestParam(value = "id") int id) {
        return new Word();
    }

    /**
     * 添加单词及其在书本中的例句
     *
     * @param query   待添加的单词
     * @param example 书本中的例句
     * @return 添加结果
     */
    @RequestMapping("/add")
    public boolean add(@RequestParam(value = "query") String query, @RequestParam(value = "example") String example) {
        return true;
    }

    /**
     * 更新单词例句
     *
     * @param id      单词ID
     * @param example 例句
     * @return 更新结果
     */
    @RequestMapping("/update")
    public boolean update(@RequestParam(value = "id") long id, @RequestParam(value = "example") String example) {
        return true;
    }

    /**
     * 删除单词
     *
     * @param id 单词ID
     * @return 删除结果
     */
    @RequestMapping("/delete")
    public boolean delete(@RequestParam(value = "id") long id) {
        return true;
    }
}
