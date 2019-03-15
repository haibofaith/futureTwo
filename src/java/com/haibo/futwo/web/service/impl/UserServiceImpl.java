package com.haibo.futwo.web.service.impl;

import com.haibo.futwo.web.mappers.UserMapper;
import com.haibo.futwo.web.model.WeixinSession;
import com.haibo.futwo.web.model.WeixinUser;
import com.haibo.futwo.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:haibo.xiong
 * @date:2019/3/2
 * @description:
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<WeixinUser> login(String username, String password) {
        List<WeixinUser> weixinUsers = userMapper.login(username,password);
        return weixinUsers;
    }

    @Override
    public int onLogin(WeixinSession session) {
        return userMapper.onLogin(session);
    }

    @Override
    public String getOpenIdByUuid(String uuid) {
        return userMapper.getOpenIdByUuid(uuid);
    }

    @Override
    public WeixinSession getFriendSessionByNickName(String nickName) {
        WeixinSession weixinSession = userMapper.getFriendSessionByNickName(nickName);
        return weixinSession;
    }

    @Override
    public WeixinSession getUserSessionByUuid(String uuid) {
        WeixinSession weixinSession = userMapper.getUserSessionByUuid(uuid);
        return weixinSession;
    }

    @Override
    public List<WeixinSession> searchUsersByNickName(String nickName) {
        return userMapper.searchUsersByNickName(nickName);
    }


}
