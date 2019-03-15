package com.haibo.futwo.web.service;

import com.haibo.futwo.web.datasource.DataSource;
import com.haibo.futwo.web.datasource.DataSourceConstant;
import com.haibo.futwo.web.model.WeixinSession;
import com.haibo.futwo.web.model.WeixinUser;

import java.util.List;


public interface UserService {
    @DataSource(DataSourceConstant.DATA_SOURCE_LOCAL)
    List<WeixinUser> login(String username,String password);
    @DataSource(DataSourceConstant.DATA_SOURCE_LOCAL)
    int onLogin(WeixinSession session);
    @DataSource(DataSourceConstant.DATA_SOURCE_LOCAL)
    String getOpenIdByUuid(String uuid);
    @DataSource(DataSourceConstant.DATA_SOURCE_LOCAL)
    WeixinSession getFriendSessionByNickName(String nickName);
    @DataSource(DataSourceConstant.DATA_SOURCE_LOCAL)
    WeixinSession getUserSessionByUuid(String uuid);
    @DataSource(DataSourceConstant.DATA_SOURCE_LOCAL)
    List<WeixinSession> searchUsersByNickName(String nickName);
}
