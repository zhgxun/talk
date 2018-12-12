package com.github.zhgxun.talk.service.impl;

import com.github.zhgxun.talk.common.enums.UserRole;
import com.github.zhgxun.talk.common.enums.UserType;
import com.github.zhgxun.talk.common.processor.impl.WeiboLoginProcessor;
import com.github.zhgxun.talk.common.util.DateUtil;
import com.github.zhgxun.talk.config.Constant;
import com.github.zhgxun.talk.dao.UserDao;
import com.github.zhgxun.talk.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private WeiboLoginProcessor weiboLoginProcessor;

    @Autowired
    private UserDao userDao;

    @Override
    public String accessUrl(UserType type) {
        switch (type) {
            case WEIBO:
                log.info("weibo");
                return weiboLoginProcessor.accessUrl(Constant.AUTH_CODE);
            case WEIXIN:
                log.info("weixin");
                return "weixin";
            case QQ:
                log.info("qq");
                return "qq";
        }
        return null;
    }

    @Override
    public String code(UserType type, String code) {
        switch (type) {
            case WEIBO:
                return weiboLoginProcessor.getCode(code);
            case WEIXIN:
                return "";
            case QQ:
                return "";
        }
        return null;
    }

    @Override
    public int add(String nickName, UserRole role, UserType type) {
        return userDao.add(nickName, role, type, 1, 1, DateUtil.getTimeStamp(), DateUtil.getTimeStamp());
    }
}
