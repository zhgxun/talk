package com.github.zhgxun.lib;

import com.github.zhgxun.models.UserInfo;
import com.github.zhgxun.util.Db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 普通用户信息
 */
public class UserInfoLib {

    /**
     * 该微信用户openId是否已经存在
     *
     * @param openId 微信用户标识
     * @return 用户是否已添加
     * @throws SQLException exception
     */
    public static boolean haveOne(String openId) throws SQLException {
        String sql = "SELECT id FROM users WHERE open_id = ?";
        try (Connection connection = Db.connection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, openId.trim());
                try (ResultSet rs = ps.executeQuery()) {
                    return rs.next();
                }
            }
        }
    }

    /**
     * 添加用户信息
     *
     * @param userInfo {@link UserInfo} 普通用户对象
     * @return 添加后的新纪录id
     * @throws SQLException exception
     */
    public static long add(UserInfo userInfo) throws SQLException {
        String sql = "INSERT INTO `user`(`open_id`, `nickname`, `avatar_url`) VALUES (?, ?, ?)";
        try (Connection connection = Db.connection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, userInfo.getOpenId());
                ps.setString(2, userInfo.getNickname());
                ps.setString(3, userInfo.getAvatarUrl());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    return rs.next() ? rs.getLong(1) : 0;
                }
            }
        }
    }

    /**
     * 根据微信平台标识获取普通用户信息
     *
     * @param openId 微信平台标识
     * @return {@link UserInfo} 普通用户对象
     * @throws SQLException exception
     */
    public static UserInfo getUserInfo(String openId) throws SQLException {
        String sql = "SELECT id, open_id, nickname, avatar_url FROM users WHERE open_id = ?";
        try (Connection connection = Db.connection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, openId.trim());
                try (ResultSet rs = ps.executeQuery()) {
                    UserInfo userInfo = new UserInfo();
                    while (rs.next()) {
                        userInfo.setId(rs.getLong("id"));
                        userInfo.setOpenId(rs.getString("open_id"));
                        userInfo.setNickname(rs.getString("nickname"));
                        userInfo.setAvatarUrl(rs.getString("avatar_url"));
                    }
                    return userInfo;
                }
            }
        }
    }
}
