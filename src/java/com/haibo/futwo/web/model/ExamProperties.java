package com.haibo.futwo.web.model;

import java.io.Serializable;

/**
 * @author:haibo.xiong
 * @date:2019/2/26
 * @description:
 */
public class ExamProperties implements Serializable{
    private String username;
    private String password;
    private String json;

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
