package com.haibo.futwo.web.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author:haibo.xiong
 * @date:2018/12/20
 * @description:
 */
public class TestUser implements Serializable{
    private Integer id;
    private String userName;
    private Date timeStamp;

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
