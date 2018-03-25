package com.github.zhgxun.words.controllers;

import com.github.zhgxun.models.Word;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 单词管理
 */
@RequestMapping("/words")
@RestController
public class Words {

    @RequestMapping("/info")
    public Word info(@RequestParam(value = "query") String query) {
        return new Word();
    }

    @RequestMapping("/add")
    public boolean add(@RequestParam(value = "query") String query, @RequestParam(value = "example") String example) {
        return true;
    }

    @RequestMapping("/update")
    public  boolean update(@RequestParam(value = "id") long id, @RequestParam(value = "example") String example) {
        return true;
    }

    @RequestMapping("/delete")
    public boolean delete(@RequestParam(value = "id") long id) {
        return true;
    }
}
