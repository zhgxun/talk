package com.github.zhgxun.talk.controller;

import com.github.zhgxun.talk.common.enums.UserType;
import com.github.zhgxun.talk.common.util.ResponseUtil;
import com.github.zhgxun.talk.config.Code;
import com.github.zhgxun.talk.entity.UserEntity;
import com.github.zhgxun.talk.manager.UserManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(path = "/user")
@Slf4j
public class UserController {

    @Autowired
    private UserManager userManager;

    /**
     * 获取用户第三方授权跳转链接
     *
     * @param type 第三方类型, 目前仅接入微博, 微信和QQ登陆
     * @return 授权链接
     */
    @GetMapping("/access")
    public ResponseUtil<String> accessUrl(
            @RequestParam(name = "type", defaultValue = "WEIBO") @NotNull(message = "参数为空") UserType type) {
        try {
            return new ResponseUtil<>(userManager.accessUrl(type));
        } catch (Exception e) {
            log.error("", e);
            return new ResponseUtil<>(Code.FAILED, e.getMessage());
        }
    }

    @GetMapping("/code")
    public ResponseUtil<UserEntity> code(
            @RequestParam(name = "type") @NotNull(message = "参数为空") UserType type,
            @RequestParam(name = "code") @NotNull(message = "参数为空") String code) {
        try {
            return new ResponseUtil<>(userManager.code(type, code));
        } catch (Exception e) {
            log.error("", e);
            return new ResponseUtil<>(Code.FAILED, e.getMessage());
        }
    }

    @GetMapping("/one")
    public ResponseUtil<UserEntity> one(@RequestParam(name = "id") @NotNull(message = "参数为空") int id) {
        try {
            return new ResponseUtil<>(userManager.findOne(id));
        } catch (Exception e) {
            log.error("", e);
            return new ResponseUtil<>(Code.FAILED, e.getMessage());
        }
    }

    @GetMapping("/any")
    public ResponseUtil<List<UserEntity>> any(@RequestParam(name = "id", required = false, defaultValue = "0") int id,
                                              @RequestParam(name = "nickName", required = false, defaultValue = "") String nickName,
                                              @RequestParam(name = "type", required = false, defaultValue = "") UserType type) {
        try {
            return new ResponseUtil<>(userManager.findAny(id, nickName, type));
        } catch (Exception e) {
            log.error("", e);
            return new ResponseUtil<>(Code.FAILED, e.getMessage());
        }
    }

    @PostMapping("/delete")
    public ResponseUtil<Boolean> delete(@RequestParam(name = "id") @NotNull(message = "参数为空") int id) {
        try {
            return new ResponseUtil<>(userManager.delete(id) > 0);
        } catch (Exception e) {
            log.error("", e);
            return new ResponseUtil<>(Code.FAILED, e.getMessage());
        }
    }
}
