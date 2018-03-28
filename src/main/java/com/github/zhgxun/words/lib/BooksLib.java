package com.github.zhgxun.words.lib;

import com.github.zhgxun.lib.BookLib;
import com.github.zhgxun.lib.WordLib;
import com.github.zhgxun.models.Book;

import java.sql.SQLException;

/**
 * 图书管理
 */
public class BooksLib {

    /**
     * 图书信息
     *
     * @param id 图书ID
     * @return {@link Book} 图书信息
     * @throws SQLException exception
     */
    public static Book info(long id) throws SQLException {
        return BookLib.getBookInfo(id);
    }

    /**
     * 添加图书
     *
     * @param title     标题
     * @param author    作者
     * @param publisher 出版社
     * @param date      出版日期
     * @return 添加成功记录ID
     * @throws SQLException exception
     */
    public static boolean add(String title, String author, String publisher, String date) throws SQLException {
        // 1. 获取用户开放平台标识

        // 2. 获取图书编号
        int number = BookLib.getNumber();

        // 3. 图书是否已经存在
        if (BookLib.haveOne(title.trim())) {
            return true;
        }

        // 4. 添加图书
        Book book = new Book();
        book.setNumber(number);
        book.setTitle(title.trim());
        book.setAuthor(author.trim());
        book.setPublisher(publisher.trim());
        book.setDate(date.trim());
        if (BookLib.add(book) <= 0) {
            return false;
        }

        // 5. 图书用户关联关系已经存在


        // 6. 添加图书用户关联关系

        // 7. 新建图书单词表
        WordLib.addTable(number);

        return true;
    }

    /**
     * 删除图书
     *
     * @param id      图书ID
     * @return 删除标识
     * @throws SQLException exception
     */
    public static boolean delete(long id) throws SQLException {

        // 2. 查询图书信息
        Book book = BookLib.getBookInfo(id);

        // 3. 获取图书编号
        int number = book.getNumber();

        // 4. 删除图书
        if (!BookLib.delete(id)) {
            return false;
        }

        // 5. 删除用户图书的关联关系


        // 6. 删除该本书对应的单词表
        WordLib.deleteTable(number);

        return true;
    }
}
