package com.haibo.futwo.web.mappers;

import com.haibo.futwo.web.model.WeixinRelation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationMapper {
    int setRelation(WeixinRelation weixinRelation);
    List<String> getFriendByOpenid(@Param("openid") String openid);
    List<WeixinRelation> getRelationInfoByOpenid(@Param("openid") String openid);

}
