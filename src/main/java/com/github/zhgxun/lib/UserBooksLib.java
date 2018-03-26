package com.github.zhgxun.lib;

import com.github.zhgxun.models.UserBooks;
import com.github.zhgxun.util.Db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户图书关联关系
 */
public class UserBooksLib {

    /**
     * 用户是否已经关联了该图书
     *
     * @param openId 用户开放平台标识
     * @param number 图书编号
     * @return 是否已关联图书
     * @throws SQLException exception
     */
    public static boolean haveOne(String openId, int number) throws SQLException {
        String sql = "SELECT id FROM user_books WHERE open_id = ? AND number = ?";
        try (Connection connection = Db.connection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, openId.trim());
                ps.setInt(2, number);
                try (ResultSet rs = ps.executeQuery()) {
                    return rs.next();
                }
            }
        }
    }

    /**
     * 添加用户图书关联信息
     *
     * @param userBooks {@link UserBooks} 图书对象
     * @return 添加后的新记录ID
     * @throws SQLException exception
     */
    public static long add(UserBooks userBooks) throws SQLException {
        String sql = "INSERT INTO `user_books`(`open_id`, `number`) VALUES (?, ?)";
        try (Connection connection = Db.connection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, userBooks.getOpenId());
                ps.setInt(2, userBooks.getNumber());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    return rs.next() ? rs.getLong(1) : 0;
                }
            }
        }
    }

    /**
     * 根据开放平台标识获取用户的图书信息
     *
     * @param openId 开放平台标识
     * @return {@link UserBooks} 用户的图书列表
     * @throws SQLException exception
     */
    public static List<UserBooks> getList(String openId) throws SQLException {
        String sql = "SELECT id, open_id, number FROM user_books WHERE open_id = ?";
        try (Connection connection = Db.connection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, openId.trim());
                try (ResultSet rs = ps.executeQuery()) {
                    List<UserBooks> list = new ArrayList<>();
                    while (rs.next()) {
                        UserBooks userBooks = new UserBooks();
                        userBooks.setId(rs.getLong("id"));
                        userBooks.setOpenId(rs.getString("open_id"));
                        userBooks.setNumber(rs.getInt("number"));
                        list.add(userBooks);
                    }
                    return list;
                }
            }
        }
    }

    /**
     * 删除用户图书关联信息
     *
     * @param openId 用户开放平台标识
     * @param number 图书编号
     * @return 删除关联关系
     * @throws SQLException exception
     */
    public static boolean delete(String openId, int number) throws SQLException {
        String sql = "DELETE FROM user_books WHERE open_id = ? AND number = ?";
        try (Connection connection = Db.connection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, openId.trim());
                ps.setInt(2, number);
                ps.executeUpdate();
                return true;
            }
        }
    }
}
