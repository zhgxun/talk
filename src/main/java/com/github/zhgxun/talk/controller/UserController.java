package com.github.zhgxun.talk.controller;

import com.github.zhgxun.talk.common.enums.UserType;
import com.github.zhgxun.talk.common.util.ResponseUtil;
import com.github.zhgxun.talk.config.Code;
import com.github.zhgxun.talk.entity.UserEntity;
import com.github.zhgxun.talk.manger.UserManger;
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
    private UserManger userManger;

    /**
     * 获取用户第三方授权跳转链接
     *
     * @param type 第三方类型, 目前仅接入微博, 微信和QQ登陆
     * @return 授权链接
     */
    @GetMapping("/access")
    public ResponseUtil<String> accessUrl(
            @RequestParam(name = "type", defaultValue = "WEIBO") @NotNull(message = "参数为空") UserType type) {
        log.info("type: {}", type);
        ResponseUtil<String> res = new ResponseUtil<>();
        try {
            res.setCode(Code.SUCCESS);
            res.setData(userManger.accessUrl(type));
        } catch (Exception e) {
            log.error("", e);
            res.setCode(Code.FAILED);
            res.setMessage(e.getMessage());
        }
        return res;
    }

    @GetMapping("/code")
    public ResponseUtil<UserEntity> code(
            @RequestParam(name = "type") @NotNull(message = "参数为空") UserType type,
            @RequestParam(name = "code") @NotNull(message = "参数为空") String code) {
        log.info("type: {}, code: {}", type, code);
        ResponseUtil<UserEntity> res = new ResponseUtil<>();
        try {
            res.setCode(Code.SUCCESS);
            res.setData(userManger.code(type, code));
        } catch (Exception e) {
            log.error("", e);
            res.setCode(Code.FAILED);
            res.setMessage(e.getMessage());
        }
        return res;
    }

    @GetMapping("/one")
    public ResponseUtil<UserEntity> one(@RequestParam(name = "id") @NotNull(message = "参数为空") int id) {
        ResponseUtil<UserEntity> res = new ResponseUtil<>();
        try {
            res.setCode(Code.SUCCESS);
            res.setData(userManger.findOne(id));
        } catch (Exception e) {
            log.error("", e);
            res.setCode(Code.FAILED);
            res.setMessage(e.getMessage());
        }
        return res;
    }

    @GetMapping("/any")
    public ResponseUtil<List<UserEntity>> any(@RequestParam(name = "id") int id,
                                              @RequestParam(name = "nickName") String nickName,
                                              @RequestParam(name = "type") UserType type) {
        log.info("id: {}, nickName: {}, type: {}", id, nickName, type);
        ResponseUtil<List<UserEntity>> res = new ResponseUtil<>();
        try {
            res.setCode(Code.SUCCESS);
            res.setData(userManger.findAny(id, nickName, type));
        } catch (Exception e) {
            log.error("", e);
            res.setCode(Code.FAILED);
            res.setMessage(e.getMessage());
        }
        return res;
    }

    @PostMapping("/delete")
    public ResponseUtil<Boolean> delete(@RequestParam(name = "id") @NotNull(message = "参数为空") int id) {
        ResponseUtil<Boolean> res = new ResponseUtil<>();
        try {
            res.setCode(Code.SUCCESS);
            res.setData(userManger.delete(id) > 0);
        } catch (Exception e) {
            log.error("", e);
            res.setCode(Code.FAILED);
            res.setMessage(e.getMessage());
        }
        return res;
    }
}
