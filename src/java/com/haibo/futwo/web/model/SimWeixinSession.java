package com.haibo.futwo.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author:haibo.xiong
 * @date:2019/3/5
 * @description:
 */
public class SimWeixinSession implements Serializable{

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
    private String uuid;
    private String nickName;
    private String avatarUrl;

    public SimWeixinSession() {
    }

    public SimWeixinSession(String uuid, String nickName, String avatarUrl) {
        this.uuid = uuid;
        this.nickName = nickName;
        this.avatarUrl = avatarUrl;
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

}
