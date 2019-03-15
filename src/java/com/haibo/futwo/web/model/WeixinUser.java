package com.haibo.futwo.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author:haibo.xiong
 * @date:2019/3/2
 * @description: `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
 * `username` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名',
 * `phone` varchar(25) NOT NULL DEFAULT '' COMMENT '手机号',
 * `password` varchar(50) NOT NULL DEFAULT '' COMMENT '密码',
 * `status` tinyint(4) NOT NULL DEFAULT '-1' COMMENT '用户状态位(-1|0|1：默认|无效|有效)',
 * `_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
 */
public class WeixinUser implements Serializable {
    private Long id;
    private String userName;
    private String phone;
    private String passWord;
    private Integer status;
    private Date timestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
