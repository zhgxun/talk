package com.github.zhgxun.words.lib;

import com.github.zhgxun.lib.BookLib;
import com.github.zhgxun.lib.UserBooksLib;
import com.github.zhgxun.lib.UserLib;
import com.github.zhgxun.lib.WordLib;
import com.github.zhgxun.models.Book;
import com.github.zhgxun.models.UserBooks;

import java.sql.SQLException;
import java.util.List;

/**
 * 图书管理
 */
public class BooksLib {

    /**
     * 获取用户图书列表
     *
     * @param encrypt 加密用户信息
     * @param iv      初始向量
     * @return {@link Book} 图书信息
     * @throws SQLException exception
     */
    public static List<Book> books(String encrypt, String iv) throws SQLException {
        // 1. 获取开放平台标识
        String openId = UserLib.getOpenId(encrypt, iv);
        if (openId == null) {
            return null;
        }

        // 2. 获取用户图书关联信息
        StringBuilder builder = new StringBuilder();
        List<UserBooks> userBooks = UserBooksLib.getList(openId);
        for (UserBooks books : userBooks) {
            builder.append(String.valueOf(books.getNumber()));
            builder.append(",");
        }
        if (builder.length() <= 0) {
            return null;
        }
        String numberStr = builder.toString();
        String numbers = numberStr.substring(0, numberStr.length() - 1);

        // 3. 获取用户图书信息
        return BookLib.getList(numbers);
    }

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
        if (UserBooksLib.haveOne(openId, number)) {
            return true;
        }

        // 6. 添加图书用户关联关系
        UserBooks userBooks = new UserBooks();
        userBooks.setOpenId(openId);
        userBooks.setNumber(number);
        if (UserBooksLib.add(userBooks) <= 0) {
            return false;
        }

        // 7. 新建图书单词表
        WordLib.addTable(number);

        return true;
    }

    /**
     * 删除图书
     *
     * @param id      图书ID
     * @param encrypt 加密用户信息
     * @param iv      初始向量
     * @return 删除标识
     * @throws SQLException exception
     */
    public static boolean delete(long id, String encrypt, String iv) throws SQLException {
        // 1. 获取用户开放平台标识
        String openId = UserLib.getOpenId(encrypt, iv);
        if (openId == null) {
            return false;
        }

        // 2. 查询图书信息
        Book book = BookLib.getBookInfo(id);

        // 3. 获取图书编号
        int number = book.getNumber();

        // 4. 删除图书
        if (!BookLib.delete(id)) {
            return false;
        }

        // 5. 删除用户图书的关联关系
        if (!UserBooksLib.delete(openId, number)) {
            return false;
        }

        // 6. 删除该本书对应的单词表
        WordLib.deleteTable(number);

        return true;
    }
}
