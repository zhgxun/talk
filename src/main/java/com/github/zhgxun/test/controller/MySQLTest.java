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
    private JdbcTemplate jdbcTemplate;

    public static void main(String args[]) {
        logger.info("start test MySQL connect...");

        // 1. 添加一本书 《Java核心技术: 第10版, 卷I. 基础知识》
        BookLib bookLib = new BookLib();
        Book book = new Book();
        // 编号
        book.setNumber(bookLib.getNumber());
        // Core Java Volume I-Fundamentals, Tenth Edition
        book.setTitle("Java核心技术: 第10版, 卷I. 基础知识");
        // 作者
        book.setAuthor("(美)霍斯特曼(Horstmann,C.S.)");
        // 出版社
        book.setPublisher("北京：人民邮电出版社");
        // 出版时间
        book.setDate("2016.6(2017.7重印)");

        // 2. 图书是否已经存在
        if (bookLib.havaOne(book.getTitle())) {
            logger.info("该书: " + book.getTitle() + " 已经存在, 无需再添加");
        } else {
            // 3. 添加图书
            int id = bookLib.add(book);
            logger.info("图书添加成功, 新的图书id= " + id);
        }
        // 4. 查询条件结果
        MySQLTest test = new MySQLTest();
        test.mysql();

        logger.info("test end.");
    }

    /**
     * 测试mysql
     */
    private void mysql() {
        try {
            Book book = jdbcTemplate.queryForObject("SELECT * FROM books LIMIT 1", Book.class);
            logger.info(book.getAuthor());
            logger.info(book.getTitle());
        } catch (NullPointerException e) {
            logger.info("空指针异常: " + e.getMessage());
        }
    }
}
