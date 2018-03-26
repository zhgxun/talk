package com.github.zhgxun.words.lib;

import com.github.zhgxun.lib.BookLib;
import com.github.zhgxun.lib.UserBooksLib;
import com.github.zhgxun.lib.UserLib;
import com.github.zhgxun.lib.WordLib;
import com.github.zhgxun.models.Book;
import com.github.zhgxun.models.User;
import com.github.zhgxun.models.UserBooks;
import com.github.zhgxun.util.Aes;

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
     * @param encrypt   加密信息
     * @param iv        初始向量
     * @return 添加成功记录ID
     * @throws SQLException exception
     */
    public static boolean add(String title, String author, String publisher, String date, String encrypt, String iv) throws SQLException {
        // 1. 获取用户开放平台标识
        String openId = UserLib.getOpenId(encrypt, iv);
        if (openId == null) {
            return false;
        }

        // 2. 获取图书编号
        int number = BookLib.getNumber();

        // 3. 是否已有图书存在
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
        BookLib.add(book);

        // 5. 添加图书用户关联关系
        if (!UserBooksLib.haveOne(openId, number)) {
            UserBooks userBooks = new UserBooks();
            userBooks.setOpenId(openId);
            userBooks.setNumber(number);
            UserBooksLib.add(userBooks);
        }

        // 4. 新建图书单词表
        WordLib.addTable(number);

        return true;
    }

    public static boolean delete(long id, String encrypt, String iv) {
        // 获取用户开放平台标识
        String openId = UserLib.getOpenId(encrypt, iv);
        if (openId == null) {
            return false;
        }

        // 删除用户图书的关联关系


        // 删除这本书

        // 删除该本书的单词表
        return true;
    }
}
