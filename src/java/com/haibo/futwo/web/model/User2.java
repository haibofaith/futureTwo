package com.haibo.futwo.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author:haibo.xiong
 * @date:2019/1/15
 * @description:
 */
public class User2 implements Serializable{
    private static final long serialVersionUID = 1L;

    /**用户名*/
    private String loginName;

    /**用户密码*/
    private String password;

    /**手机号*/
    private String mobile;

    /**创建时间*/
    private Date createTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User2{" +
                "loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
