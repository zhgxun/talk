package com.github.zhgxun.talk.manger.impl;

import com.github.zhgxun.talk.common.enums.UserRole;
import com.github.zhgxun.talk.common.enums.UserType;
import com.github.zhgxun.talk.entity.UserEntity;
import com.github.zhgxun.talk.manger.UserManger;
import com.github.zhgxun.talk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserMangerImpl implements UserManger {

    @Autowired
    private UserService userService;

    @Override
    public String accessUrl(UserType type) {
        return userService.accessUrl(type);
    }

    @Override
    public String code(UserType type, String code) {
        return userService.code(type, code);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int add(String nickName, UserRole role, UserType type) {
        UserEntity entity = new UserEntity();
        entity.setNickName(nickName);
        entity.setRole(role);
        entity.setType(type);
        return userService.add(entity);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public UserEntity findOne(int id) {
        return userService.findOne(id);
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
