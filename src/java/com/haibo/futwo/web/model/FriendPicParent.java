package com.haibo.futwo.web.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author:haibo.xiong
 * @date:2019/3/8
 * @description:
 */
public class FriendPicParent implements Serializable{
    private String nickName;
    private List<String> picParentPaths;
    private String openid;

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

    public List<String> getPicParentPaths() {
        return picParentPaths;
    }

    public void setPicParentPaths(List<String> picParentPaths) {
        this.picParentPaths = picParentPaths;
    }
}
