package com.github.zhgxun.talk.controller;

import com.github.zhgxun.talk.common.enums.UserType;
import com.github.zhgxun.talk.common.util.ResponseUtil;
import com.github.zhgxun.talk.config.Code;
import com.github.zhgxun.talk.entity.UserEntity;
import com.github.zhgxun.talk.manager.UserManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@Api(tags = {"用户管理"})
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
    @ApiOperation(value = "获取用户第三方授权跳转链接")
    @ApiImplicitParam(name = "type", value = "第三方类型, 目前仅接入微博, 微信和QQ登陆", defaultValue = "WEIBO", required = true, paramType = "query", dataType = "String")
    @RequestMapping(path = "/access", method = RequestMethod.GET)
    public ResponseUtil<String> accessUrl(
            @RequestParam(name = "type", defaultValue = "WEIBO") @NotNull(message = "参数为空") UserType type) {
        try {
            return new ResponseUtil<>(userManager.accessUrl(type));
        } catch (Exception e) {
            log.error("", e);
            return new ResponseUtil<>(Code.FAILED, e.getMessage());
        }
    }

    @ApiOperation(value = "第三方授权平台回调地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "第三方平台类型, 开发者配置回传参数", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "用户授权第三方平台后回调时附带的认证码", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(path = "/code", method = RequestMethod.GET)
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

    @ApiOperation(value = "用户详情")
    @ApiImplicitParam(name = "id", value = "用户标识", defaultValue = "0", required = true, paramType = "query", dataType = "int")
    @RequestMapping(path = "/one", method = RequestMethod.GET)
    public ResponseUtil<UserEntity> one(@RequestParam(name = "id", defaultValue = "0") @NotNull(message = "参数为空") int id) {
        try {
            return new ResponseUtil<>(userManager.findOne(id));
        } catch (Exception e) {
            log.error("", e);
            return new ResponseUtil<>(Code.FAILED, e.getMessage());
        }
    }

    @ApiOperation(value = "用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户标识", defaultValue = "0", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "nickName", value = "用户昵称", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "type", value = "用户第三方平台类型", paramType = "query", dataType = "String")
    })
    @RequestMapping(path = "/any", method = RequestMethod.GET)
    public ResponseUtil<List<UserEntity>> any(@RequestParam(name = "id", required = false, defaultValue = "0") int id,
                                              @RequestParam(name = "nickName", required = false) String nickName,
                                              @RequestParam(name = "type", required = false) UserType type) {
        try {
            return new ResponseUtil<>(userManager.findAny(id, nickName, type));
        } catch (Exception e) {
            log.error("", e);
            return new ResponseUtil<>(Code.FAILED, e.getMessage());
        }
    }

    @ApiOperation(value = "用户删除", notes = "慎用, 通常无需使用, 近删除用户信息, 不删除用户相关数据")
    @ApiImplicitParam(name = "id", value = "用户标识", defaultValue = "0", required = true, paramType = "query", dataType = "int")
    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public ResponseUtil<Boolean> delete(@RequestParam(name = "id", defaultValue = "0") @NotNull(message = "参数为空") int id) {
        try {
            return new ResponseUtil<>(userManager.delete(id) > 0);
        } catch (Exception e) {
            log.error("", e);
            return new ResponseUtil<>(Code.FAILED, e.getMessage());
        }
    }
}
