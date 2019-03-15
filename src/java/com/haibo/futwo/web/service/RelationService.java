package com.haibo.futwo.web.service;

import com.haibo.futwo.web.datasource.DataSource;
import com.haibo.futwo.web.datasource.DataSourceConstant;
import com.haibo.futwo.web.model.WeixinRelation;

import java.util.List;

public interface RelationService {
    @DataSource(DataSourceConstant.DATA_SOURCE_LOCAL)
    int setRelation(WeixinRelation weixinRelation);
    @DataSource(DataSourceConstant.DATA_SOURCE_LOCAL)
    List<String> getFriendByOpenid(String openid);
    @DataSource(DataSourceConstant.DATA_SOURCE_LOCAL)
    List<WeixinRelation> getRelationInfoByOpenid(String openid);
}
