package com.haibo.futwo.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author:haibo.xiong
 * @date:2019/3/11
 * @description: CREATE TABLE `weixin_questions` (
 * `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
 * `openid` varchar(50) NOT NULL DEFAULT '' COMMENT 'user_openid',
 * `nick_name` varchar(200) NOT NULL DEFAULT '' COMMENT 'user_nick_name',
 * `uuid` varchar(50) NOT NULL DEFAULT '' COMMENT 'user_uuid',
 * `q_num` varchar(200) NOT NULL DEFAULT '问题编号',
 * `question` varchar(200) NOT NULL DEFAULT '问题内容',
 * `_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
 * PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='小程序调查问卷集';
 */
public class Question implements Serializable {
    private Long id;
    private String uuid;
    private String openid;
    private String nickName;
    private String qNum;
    private String question;
    private String qTitle;

    public String getqTitle() {
        return qTitle;
    }

    public void setqTitle(String qTitle) {
        this.qTitle = qTitle;
    }

    private Date timestamp;


    public Question() {
    }

    public Question(String uuid, String openid, String nickName, String qNum, String question, String qTitle) {
        this.uuid = uuid;
        this.openid = openid;
        this.nickName = nickName;
        this.qNum = qNum;
        this.question = question;
        this.qTitle = qTitle;
    }

    public Question(String uuid, String openid, String nickName, String qNum, String question) {
        this.uuid = uuid;
        this.openid = openid;
        this.nickName = nickName;
        this.qNum = qNum;
        this.question = question;
    }

    public String getqNum() {
        return qNum;
    }

    public void setqNum(String qNum) {
        this.qNum = qNum;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
