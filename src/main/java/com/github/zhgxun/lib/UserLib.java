package com.github.zhgxun.lib;

import com.github.zhgxun.models.User;
import com.github.zhgxun.util.Db;
import com.github.zhgxun.util.Log;

import java.sql.*;

/**
 * 用户信息
 */
public class UserLib {

    /**
     * 加密用的盐
     */
    public final static String salt = "TCg@6^kxReMzJc!nsd^edUtrpZC9nD0O";

    /**
     * 用户是否已经存在
     *
     * @param name 昵称
     * @return 是否存在
     * @throws SQLException exception
     */
    public static boolean haveOne(String name) throws SQLException {
        String sql = "SELECT id FROM users WHERE name = ?";
        Log.logger.info("UserLib::haveOne, sql= " + sql + ", name= " + name);
        try (Connection connection = Db.connection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, name.trim());
                try (ResultSet rs = ps.executeQuery()) {
                    return rs.next();
                }
            }
        }
    }

    /**
     * 添加用户信息
     *
     * @param user {@link User} 用户对象
     * @return 添加后的记录ID
     * @throws SQLException exception
     */
    public static long add(User user) throws SQLException {
        String sql = "INSERT INTO `users`(`name`, `password`) VALUES (?, ?)";
        Log.logger.info("UserLib::add, sql= " + sql + ", name= " + user.getName() + ", password= " + user.getPassword());
        try (Connection connection = Db.connection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, user.getName());
                ps.setString(2, user.getPassword());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    return rs.next() ? rs.getLong(1) : 0;
                }
            }
        }
    }

    /**
     * 通过用户ID获取用户信息
     *
     * @param id 用户ID
     * @return {@link User} 用户对象
     * @throws SQLException exception
     */
    public static User info(long id) throws SQLException {
        String sql = "SELECT id, name, password FROM users WHERE id = ?";
        Log.logger.info("UserLib::info, sql= " + sql + ", id= " + id);
        try (Connection connection = Db.connection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setLong(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    User user = new User();
                    while (rs.next()) {
                        user.setId(rs.getLong("id"));
                        user.setName(rs.getString("name"));
                        user.setPassword(rs.getString("password"));
                    }
                    return user;
                }
            }
        }
    }

    /**
     * 通过用户昵称获取用户信息
     *
     * @param name 用户昵称
     * @return {@link User} 用户对象
     * @throws SQLException exception
     */
    public static User info(String name) throws SQLException {
        String sql = "SELECT id, name, password FROM users WHERE name = ?";
        Log.logger.info("UserLib::info, sql= " + sql + ", id= " + name);
        try (Connection connection = Db.connection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, name.trim());
                try (ResultSet rs = ps.executeQuery()) {
                    User user = new User();
                    while (rs.next()) {
                        user.setId(rs.getLong("id"));
                        user.setName(rs.getString("name"));
                        user.setPassword(rs.getString("password"));
                    }
                    return user;
                }
            }
        }
    }
}
