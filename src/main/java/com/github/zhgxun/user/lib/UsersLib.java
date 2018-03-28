package com.github.zhgxun.user.lib;

import com.github.zhgxun.lib.UserLib;
import com.github.zhgxun.models.User;
import com.github.zhgxun.util.Log;
import com.github.zhgxun.util.Md5;

import java.sql.SQLException;

/**
 * 用户信息处理
 */
public class UsersLib {

    /**
     * 添加用户
     *
     * @param name     用户名
     * @param password 密码
     * @return 添加结果
     * @throws SQLException exception
     */
    public static boolean add(String name, String password) throws SQLException {
        Log.logger.info("UsersLib::add, name= " + name + ", password: " + password);
        // 用户是否存在
        if (UserLib.haveOne(name)) {
            return true;
        }

        // 添加用户
        User user = new User();
        user.setName(name);
        user.setPassword(Md5.md5(password + UserLib.salt));
        return UserLib.add(user) >= 1;
    }

    /**
     * 根据用户昵称获取用户信息
     *
     * @param name 用户昵称
     * @return {@link User} 用户对象
     * @throws SQLException exception
     */
    public static User info(String name) throws SQLException {
        return UserLib.info(name);
    }

    /**
     * 根据用户ID获取用户信息
     *
     * @param id 用户ID
     * @return {@link User} 用户对象
     * @throws SQLException exception
     */
    public static User info(long id) throws SQLException {
        return UserLib.info(id);
    }
}
