package com.github.zhgxun.lib;

import com.github.zhgxun.models.Book;
import com.github.zhgxun.util.Db;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public Integer getNumber() throws SQLException {
        String sql = "SELECT MAX(number) max FROM books";
        try (Connection connection = Db.connection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                try (ResultSet rs = ps.executeQuery()) {
                    Integer id = 0;
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
    public boolean haveOne(String title) throws SQLException {
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
    public long add(Book book) throws SQLException {
        String sql = "INSERT INTO `books`(`number`, `title`, `author`, `publisher`, `date`) VALUES(%d, '%s', '%s', '%s', '%s')";
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
}
