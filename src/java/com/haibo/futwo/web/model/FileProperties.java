package com.haibo.futwo.web.model;

import java.io.Serializable;

/**
 * @author:haibo.xiong
 * @date:2019/2/28
 * @description:
 */
public class FileProperties implements Serializable{
    private String uploadpath;
    private String localpath;

    public String getLocalpath() {
        return localpath;
    }

    public void setLocalpath(String localpath) {
        this.localpath = localpath;
    }

    public String getUploadpath() {
        return uploadpath;
    }

    public void setUploadpath(String uploadpath) {
        this.uploadpath = uploadpath;
    }
}
