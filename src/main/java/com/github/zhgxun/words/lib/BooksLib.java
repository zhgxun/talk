package com.github.zhgxun.words.lib;

import com.github.zhgxun.lib.BookLib;
import com.github.zhgxun.models.Book;

import java.sql.SQLException;

/**
 * 图书管理
 */
public class BooksLib {

    /**
     * 图书信息
     *
     * @param title 图书标题
     * @return {@link Book} 图书信息
     * @throws SQLException exception
     */
    public static Book info(String title) throws SQLException {
        return BookLib.getBookInfo(title);
    }

    /**
     * 添加图书
     *
     * @param title     标题
     * @param author    作者
     * @param publisher 出版社
     * @param date      出版日期
     * @return 添加成功记录ID
     * @throws SQLException
     */
    public static boolean add(String title, String author, String publisher, String date) throws SQLException {
        Book book = new Book();
        book.setNumber(BookLib.getNumber());
        book.setTitle(title.trim());
        book.setAuthor(author.trim());
        book.setPublisher(publisher.trim());
        book.setDate(date.trim());

        return BookLib.add(book) >= 1;
    }

    public static boolean update(long id) {
        return true;
    }

    public static boolean delete(long id) {
        return true;
    }
}
