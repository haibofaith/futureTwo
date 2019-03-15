package com.haibo.futwo.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author:haibo.xiong
 * @date:2019/3/5
 * @description:
 */
public class WeixinSession implements Serializable{

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * session_key : 4AUGUjSAPZ/l3m+WnTZ2QA==
     * openid : oY2gZ4-aslQ4HCU-qyJZ9jQ2neCs
     */
    private Long id;
    private String uuid;
    private String session_key;
    private String openid;
    private String nickName;
    private String avatarUrl;
    private Date timestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Override
    public String toString() {
        return "WeixinSession{" +
                "uuid='" + uuid + '\'' +
                ", session_key='" + session_key + '\'' +
                ", openid='" + openid + '\'' +
                '}';
    }
}
