package com.github.zhgxun.talk.manager.impl;

import com.github.zhgxun.talk.common.enums.UserRole;
import com.github.zhgxun.talk.common.enums.UserType;
import com.github.zhgxun.talk.common.processor.bean.ThirdUserPart;
import com.github.zhgxun.talk.entity.UserEntity;
import com.github.zhgxun.talk.manager.UserManager;
import com.github.zhgxun.talk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserManagerImpl implements UserManager {

    @Autowired
    private UserService userService;

    @Override
    public String accessUrl(UserType type) {
        return userService.accessUrl(type);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public UserEntity code(UserType type, String code) {
        // 1. 收到回调后即添加用户, 用户存在即为已授权成功直接返回成功即可
        String codes = userService.code(type, code);

        // 2. 通过code查询用户权限和基本信息
        ThirdUserPart part = userService.part(type, codes);
        if (part == null) {
            return null;
        }

        // 3. 保存用户相关
        UserEntity entity = new UserEntity();
        entity.setOauthId(part.getOauthId());
        entity.setNickName(part.getName());
        entity.setHome(part.getHome());
        entity.setUrl(part.getUrl());
        // 管理员通过修改数据行记录, 全部默认为普通用户
        entity.setRole(UserRole.NONE);
        entity.setType(type);
        userService.add(entity, part);
        return userService.findOne(entity.getId(), null, 0);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public UserEntity findOne(int id) {
        return userService.findOne(id, null, 0);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<UserEntity> findAny(int id, String nickName, UserType type) {
        return userService.findAny(id, nickName, type.getValue());
    }

    @Override
    public int delete(int id) {
        return userService.delete(id);
    }
}
