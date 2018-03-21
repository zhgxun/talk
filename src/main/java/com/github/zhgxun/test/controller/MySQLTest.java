package com.github.zhgxun.test.controller;

import com.github.zhgxun.lib.BookLib;
import com.github.zhgxun.models.Book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 测试数据库连接
 */
public class MySQLTest {

    /**
     * 获取一个日志工厂
     */
    private static final Logger logger = LoggerFactory.getLogger(MySQLTest.class);

    /**
     * 注解JdbcTemplate自动连接数据库
     */
    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String args[]) {
        BookLib bookLib = new BookLib();
        Integer id = bookLib.getNumber();
        System.out.println("id: " + id);

        MySQLTest test = new MySQLTest();
        test.mysql();
    }

    /**
     * 测试mysql是否连接
     */
    private void mysql() {
        logger.info("start test MySQL connect...");
        try {
            Book book = jdbcTemplate.queryForObject("SELECT * FROM books LIMIT 1", Book.class);
            logger.info(book.getAuthor());
            logger.info(book.getTitle());
        } catch (NullPointerException e) {
            logger.info("空指针异常: " + e.getMessage());
        }
        logger.info("test end.");
    }
}
