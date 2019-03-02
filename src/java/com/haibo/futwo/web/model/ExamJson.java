package com.haibo.futwo.web.model;

import java.io.Serializable;

/**
 * @author:haibo.xiong
 * @date:2019/2/26
 * @description:
 */
public class ExamJson implements Serializable{
    private Integer orderno;
    private String name;

    public Integer getOrderno() {
        return orderno;
    }

    public void setOrderno(Integer orderno) {
        this.orderno = orderno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
