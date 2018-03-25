package com.github.zhgxun.words.controllers;

import com.github.zhgxun.models.Book;

import com.github.zhgxun.words.lib.BooksLib;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 图书管理
 */
@RequestMapping("/books")
@RestController
public class Books {

    /**
     * 根据图书标题查询图书
     *
     * @param title 图书标题
     * @return {@link Book} 图书对象
     */
    @RequestMapping("/info")
    public Book info(@RequestParam(value = "title") String title) {
        try {
            return BooksLib.info(title);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 添加图书
     *
     * @param title     标题
     * @param author    作者
     * @param publisher 出版社
     * @param date      出版日期
     * @return 添加结果
     */
    @RequestMapping("/add")
    public boolean add(@RequestParam(value = "title") String title, @RequestParam(value = "author") String author, @RequestParam(value = "publisher") String publisher, @RequestParam(value = "date") String date) {
        try {
            return BooksLib.add(title, author, publisher, date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequestMapping("/update")
    public boolean update(@RequestParam(value = "id") long id, @RequestParam(value = "title") String title) {
        return true;
    }

    @RequestMapping("/delete")
    public boolean delete(@RequestParam(value = "id") long id) {
        return true;
    }
}
