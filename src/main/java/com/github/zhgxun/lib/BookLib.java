package com.github.zhgxun.lib;

import com.github.zhgxun.models.Book;
import com.github.zhgxun.util.Db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 图书类库
 */
public class BookLib {

    /**
     * 生成一个唯一数字
     *
     * @return 编号, 不依赖于主键, 主键可能被迁移或表发生更改时发生变化
     * @throws SQLException exception
     */
    public static int getNumber() throws SQLException {
        String sql = "SELECT MAX(number) max FROM books";
        try (Connection connection = Db.connection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                try (ResultSet rs = ps.executeQuery()) {
                    int id = 0;
                    while (rs.next()) {
                        id = rs.getInt("max");
                    }
                    return id;
                }
            }
        }
    }

    /**
     * 图书是否存在
     *
     * @param title 图书标题
     * @return boolean
     * @throws SQLException exception
     */
    public static boolean haveOne(String title) throws SQLException {
        String sql = "SELECT id FROM books WHERE title = ?";
        try (Connection connection = Db.connection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, title.trim());
                try (ResultSet rs = ps.executeQuery()) {
                    return rs.next();
                }
            }
        }
    }

    /**
     * 添加图书
     *
     * @param book 图书对象
     * @return 新增id, 正确时新增id >= 1
     * @throws SQLException exception
     */
    public static long add(Book book) throws SQLException {
        String sql = "INSERT INTO `books`(`number`, `title`, `author`, `publisher`, `date`) VALUES(?, ?, ?, ?, ?)";
        try (Connection connection = Db.connection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, book.getNumber());
                ps.setString(2, book.getTitle());
                ps.setString(3, book.getAuthor());
                ps.setString(4, book.getPublisher());
                ps.setString(5, book.getDate());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    return rs.next() ? rs.getLong(1) : 0;
                }
            }
        }
    }

    /**
     * 根据图书名称获取图书信息
     *
     * @param title 图书名称
     * @return {@link Book} 图书对象
     * @throws SQLException exception
     */
    public static Book getBookInfo(String title) throws SQLException {
        String sql = "SELECT id, number, title, author, publisher, date FROM books WHERE title = ?";
        try (Connection connection = Db.connection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, title.trim());
                try (ResultSet rs = ps.executeQuery()) {
                    Book book = new Book();
                    while (rs.next()) {
                        book.setId(rs.getLong("id"));
                        book.setNumber(rs.getInt("number"));
                        book.setTitle(rs.getString("title"));
                        book.setPublisher(rs.getString("publisher"));
                        book.setDate(rs.getString("date"));
                    }
                    return book;
                }
            }
        }
    }

    /**
     * 根据图书ID获取图书信息
     *
     * @param id 图书ID
     * @return {@link Book} 图书对象
     * @throws SQLException exception
     */
    public static Book getBookInfo(long id) throws SQLException {
        String sql = "SELECT id, number, title, author, publisher, date FROM books WHERE id = ?";
        try (Connection connection = Db.connection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setLong(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    Book book = new Book();
                    while (rs.next()) {
                        book.setId(rs.getLong("id"));
                        book.setNumber(rs.getInt("number"));
                        book.setTitle(rs.getString("title"));
                        book.setPublisher(rs.getString("publisher"));
                        book.setDate(rs.getString("date"));
                    }
                    return book;
                }
            }
        }
    }

    /**
     * 根据图书ID列表获取图书信息
     *
     * @param numbers 图书ID列表
     * @return {@link Book} 图书对象
     * @throws SQLException exception
     */
    public static List<Book> getList(String numbers) throws SQLException {
        String sql = "SELECT id, number, title, author, publisher, date FROM books WHERE id IN (?)";
        try (Connection connection = Db.connection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, numbers);
                try (ResultSet rs = ps.executeQuery()) {
                    List<Book> books = new ArrayList<>();
                    while (rs.next()) {
                        Book book = new Book();
                        book.setId(rs.getLong("id"));
                        book.setNumber(rs.getInt("number"));
                        book.setTitle(rs.getString("title"));
                        book.setAuthor(rs.getString("author"));
                        book.setPublisher(rs.getString("publisher"));
                        book.setDate(rs.getString("date"));
                        books.add(book);
                    }
                    return books;
                }
            }
        }
    }

    /**
     * 删除图书
     *
     * @param id 图书主键
     * @return 删除标识
     * @throws SQLException exception
     */
    public static boolean delete(long id) throws SQLException {
        String sql = "DELETE FROM books WHERE id = ?";
        try (Connection connection = Db.connection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setLong(1, id);
                ps.executeUpdate();
                return true;
            }
        }
    }
}
