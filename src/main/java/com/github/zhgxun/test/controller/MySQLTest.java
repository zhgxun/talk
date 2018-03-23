package com.github.zhgxun.test.controller;

import com.github.zhgxun.lib.BookLib;
import com.github.zhgxun.models.Book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

/**
 * 测试数据库连接
 */
public class MySQLTest {

    /**
     * 获取一个日志工厂
     */
    private static final Logger logger = LoggerFactory.getLogger(MySQLTest.class);

    public static void main(String args[]) {
        logger.info("start test MySQL...");

        // 1. 添加一本书 《Java核心技术: 第10版, 卷I. 基础知识》
        BookLib bookLib = new BookLib();
        Book book = new Book();
        try {
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
            if (bookLib.haveOne(book.getTitle())) {
                logger.info("该书: " + book.getTitle() + " 已经存在, 无需再添加");
            } else {
                // 3. 添加图书
                long id = bookLib.add(book);
                logger.info("图书添加成功, 新的图书id= " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("test end.");
    }
}
