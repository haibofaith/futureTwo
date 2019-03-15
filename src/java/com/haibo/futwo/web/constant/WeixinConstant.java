package com.haibo.futwo.web.constant;

public enum WeixinConstant {
    weixinUser("weixinUser"),;

    private final String name;

    private WeixinConstant(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
