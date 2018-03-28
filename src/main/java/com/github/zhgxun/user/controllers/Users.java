package com.github.zhgxun.user.controllers;

import com.github.zhgxun.models.User;
import com.github.zhgxun.user.lib.UsersLib;
import com.github.zhgxun.util.Log;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 普通用户管理
 */
@RequestMapping("/users")
@RestController
public class Users {

    /**
     * 添加用户
     *
     * @param name     用户名
     * @param password 密码
     * @return 添加结果
     */
    @RequestMapping("/add")
    public boolean add(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password) {
        try {
            return UsersLib.add(name, password);
        } catch (Exception e) {
            Log.logger.warn("/users/add: " + e.getMessage());
        }
        return false;
    }

    /**
     * 通过名字查询用户是否已经存在
     *
     * @param name 昵称
     * @return {@link User} 用户对象
     */
    @RequestMapping("/name")
    public User info(@RequestParam(value = "name") String name) {
        try {
            return UsersLib.info(name);
        } catch (Exception e) {
            Log.logger.info("/users/name: " + e.getMessage());
        }
        return null;
    }

    /**
     * 通过ID查询用户信息
     *
     * @param id 用户ID
     * @return {@link User} 用户对象
     */
    @RequestMapping("/id")
    public User info(@RequestParam(value = "id") long id) {
        try {
            return UsersLib.info(id);
        } catch (Exception e) {
            Log.logger.info("/users/id: " + e.getMessage());
        }
        return null;
    }
}
