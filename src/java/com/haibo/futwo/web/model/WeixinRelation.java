package com.haibo.futwo.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author:haibo.xiong
 * @date:2019/3/7
 * @description: DROP TABLE IF EXISTS `weixin_relation`;
 * CREATE TABLE `weixin_relation` (
 * `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
 * `openid` varchar(50) NOT NULL DEFAULT '' COMMENT 'user_openid',
 * `nick_name` varchar(200) NOT NULL DEFAULT '' COMMENT 'user_nick_name',
 * `friend_openid` varchar(50) NOT NULL DEFAULT '' COMMENT 'friend_openid',
 * `friend_nick_name` varchar(200) NOT NULL DEFAULT 'friend_nick_name',
 * `_timestamp` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '时间戳',
 * PRIMARY KEY (`id`),
 * UNIQUE `ux_openid_and_friend_openid` USING BTREE (`openid`, `friend_openid`) comment '联合索引'
 * ) ENGINE=`InnoDB` AUTO_INCREMENT=1 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ROW_FORMAT=COMPACT COMMENT='小程序用户关系表' CHECKSUM=0 DELAY_KEY_WRITE=0;
 */
public class WeixinRelation implements Serializable {
    private Long id;
    private String openid;
    private String nickName;
    private String friendOpenid;
    private String friendNickName;
    private Date timestamp;

    public WeixinRelation() {
    }

    public WeixinRelation(String openid, String nickName, String friendOpenid, String friendNickName) {
        this.openid = openid;
        this.nickName = nickName;
        this.friendOpenid = friendOpenid;
        this.friendNickName = friendNickName;
    }

    public WeixinRelation(Long id, String openid, String nickName, String friendOpenid, String friendNickName, Date timestamp) {
        this.id = id;
        this.openid = openid;
        this.nickName = nickName;
        this.friendOpenid = friendOpenid;
        this.friendNickName = friendNickName;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getFriendOpenid() {
        return friendOpenid;
    }

    public void setFriendOpenid(String friendOpenid) {
        this.friendOpenid = friendOpenid;
    }

    public String getFriendNickName() {
        return friendNickName;
    }

    public void setFriendNickName(String friendNickName) {
        this.friendNickName = friendNickName;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
