package com.github.zhgxun.talk.controller;

import com.github.zhgxun.talk.common.enums.UserType;
import com.github.zhgxun.talk.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(path = "/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取用户第三方授权跳转链接
     *
     * @param type 第三方类型, 目前仅接入微博, 微信和QQ登陆
     * @return 授权链接
     */
    @GetMapping("/access")
    public String accessUrl(
            @RequestParam(name = "type", defaultValue = "WEIBO") @NotNull(message = "参数为空") UserType type) {
        log.info("type: {}", type);
        return userService.accessUrl(type);
    }

    @GetMapping("/code")
    public String code(
            @RequestParam(name = "type") @NotNull(message = "参数为空") UserType type,
            @RequestParam(name = "code") @NotNull(message = "参数为空") String code) {
        log.info("type: {}, code: {}", type, code);
        return userService.code(type, code);
    }
}
