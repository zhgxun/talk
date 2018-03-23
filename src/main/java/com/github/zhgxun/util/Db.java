package com.github.zhgxun.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 管理数据库连接, 后期如果不稳定或者开销太高可替换为连接池方式, 连接池方式速度不一定比每次连接高效, 而是保护数据库产生太多连接导致资源消耗
 */
public class Db {

    private static final String URL = "jdbc:mysql://localhost:3306/talk?useSSL=false&characterEncoding=utf8&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    /**
     * 连接数据库
     *
     * @return Connection
     * @throws SQLException exception
     */
    public static Connection connection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
