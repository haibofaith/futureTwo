package com.haibo.futwo.web.service.impl;

import com.haibo.futwo.web.mappers.RelationMapper;
import com.haibo.futwo.web.model.WeixinRelation;
import com.haibo.futwo.web.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:haibo.xiong
 * @date:2019/3/7
 * @description:
 */
@Service
public class RelationServiceImpl implements RelationService{
    @Autowired
    private RelationMapper relationMapper;
    @Override
    public int setRelation(WeixinRelation weixinRelation) {
        return relationMapper.setRelation(weixinRelation);
    }

    @Override
    public List<String> getFriendByOpenid(String openid) {
        return relationMapper.getFriendByOpenid(openid);
    }

    @Override
    public List<WeixinRelation> getRelationInfoByOpenid(String openid) {
        return relationMapper.getRelationInfoByOpenid(openid);
    }
}
