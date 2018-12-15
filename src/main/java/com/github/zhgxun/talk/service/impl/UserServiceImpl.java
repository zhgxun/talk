package com.github.zhgxun.talk.service.impl;

import com.github.zhgxun.talk.common.enums.UserType;
import com.github.zhgxun.talk.common.exception.NormalException;
import com.github.zhgxun.talk.common.processor.bean.ThirdUserPart;
import com.github.zhgxun.talk.common.processor.bean.WeiboAccessToken;
import com.github.zhgxun.talk.common.processor.impl.WeiboLoginProcessor;
import com.github.zhgxun.talk.common.util.DateUtil;
import com.github.zhgxun.talk.config.WeiboConfig;
import com.github.zhgxun.talk.dao.OauthDao;
import com.github.zhgxun.talk.dao.UserDao;
import com.github.zhgxun.talk.entity.OauthEntity;
import com.github.zhgxun.talk.entity.UserEntity;
import com.github.zhgxun.talk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private WeiboLoginProcessor weiboLoginProcessor;

    @Autowired
    private UserDao userDao;

    @Autowired
    private OauthDao oauthDao;

    @Override
    public String accessUrl(UserType type) {
        switch (type) {
            case QQ:
                return "qq";
            case WEIBO:
                return weiboLoginProcessor.accessUrl(WeiboConfig.REDIRECT_URI);
            case WEIXIN:
                return "weixin";
        }
        return null;
    }

    @Override
    public String code(UserType type, String code) {
        switch (type) {
            case QQ:
                return "";
            case WEIBO:
                return weiboLoginProcessor.getCode(code);
            case WEIXIN:
                return "";
        }
        return null;
    }

    @Override
    public ThirdUserPart part(UserType type, String code) {
        switch (type) {
            case QQ:
                break;
            case WEIBO:
                WeiboAccessToken accessToken = weiboLoginProcessor.accessToken(code);
                if (accessToken == null) {
                    return null;
                }
                return weiboLoginProcessor.userInfo(accessToken);
            case WEIXIN:
                break;
        }
        return null;
    }

    @Override
    public UserEntity add(UserEntity entity, ThirdUserPart part) {
        UserEntity userEntity = findOne(0, entity.getOauthId(), entity.getType().getValue());
        if (userEntity != null) {
            entity.setId(userEntity.getId());

            OauthEntity oauthEntity = oauthDao.findOne(userEntity.getId());
            oauthEntity.setOauthAccessToken(part.getAccessToken());
            oauthEntity.setOauthExpires(part.getExpiresIn());
            oauthEntity.setUpdateTime(DateUtil.getTimeStamp());
            update(entity, oauthEntity);
            return userEntity;
        }

        userDao.add(entity);
        int userId = entity.getId();
        OauthEntity oauth = new OauthEntity();
        oauth.setUserId(userId);
        switch (entity.getType()) {
            case QQ:
                oauth.setOauthName("腾讯QQ互联");
                break;
            case WEIBO:
                oauth.setOauthName("新浪微博");
                break;
            case WEIXIN:
                oauth.setOauthName("腾讯微信");
                break;
            default:
                oauth.setOauthName("未知平台");
        }
        oauth.setOauthAccessToken(part.getAccessToken());
        oauth.setOauthExpires(part.getExpiresIn());
        oauthDao.add(oauth);
        return entity;
    }

    @Override
    public UserEntity findOne(int id, String oauthId, int type) {
        return userDao.findOne(id, oauthId, type);
    }

    @Override
    public List<UserEntity> findAny(int id, String nickName, int type) {
        return userDao.findAny(id, nickName, type);
    }

    private void update(UserEntity entity, OauthEntity oauth) {
        oauthDao.update(oauth);
        userDao.update(entity);
    }

    @Override
    public int delete(int id) {
        UserEntity entity = userDao.findOne(id, null, 0);
        if (entity == null) {
            throw new NormalException("用户不存在");
        }
        int userId = entity.getId();
        int rs = userDao.delete(id);
        oauthDao.delete(userId);

        return rs;
    }
}
