package com.haibo.futwo.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author:haibo.xiong
 * @date:2019/3/8
 * @description:
 */
public class VoteInfo implements Serializable{
    private String id;
    private Date timestamp;
    private String openId;
    private String nickName;
    private String voteName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getVoteName() {
        return voteName;
    }

    public void setVoteName(String voteName) {
        this.voteName = voteName;
    }
}
