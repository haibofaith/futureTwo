package com.haibo.futwo.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author:haibo.xiong
 * @date:2019/3/3
 * @description: `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '图片ID',
 * `img_name` varchar(50) NOT NULL DEFAULT '' COMMENT '图片名',
 * `img_path` varchar(100) NOT NULL DEFAULT '' COMMENT '图片路径',
 * `img_file` varchar(100) NOT NULL DEFAULT '' COMMENT '图片文件夹',
 * `img_url` varchar(100) NOT NULL DEFAULT '' COMMENT '图片地址',
 * `img_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '图片日期',
 * `_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
 */
public class WeixinImg implements Serializable {
    private String open_id;
    private Long id;
    private String imgName;
    private String imgPath;
    private String imgFile;
    private String imgUrl;
    private Date imgDate;
    private Date timestamp;
    private String smallImgName;
    private String smallImgPath;
    private String smallImgFile;
    private String smallImgUrl;

    public String getSmallImgName() {
        return smallImgName;
    }

    public void setSmallImgName(String smallImgName) {
        this.smallImgName = smallImgName;
    }

    public String getSmallImgPath() {
        return smallImgPath;
    }

    public void setSmallImgPath(String smallImgPath) {
        this.smallImgPath = smallImgPath;
    }

    public String getSmallImgFile() {
        return smallImgFile;
    }

    public void setSmallImgFile(String smallImgFile) {
        this.smallImgFile = smallImgFile;
    }

    public String getSmallImgUrl() {
        return smallImgUrl;
    }

    public void setSmallImgUrl(String smallImgUrl) {
        this.smallImgUrl = smallImgUrl;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getImgFile() {
        return imgFile;
    }

    public void setImgFile(String imgFile) {
        this.imgFile = imgFile;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Date getImgDate() {
        return imgDate;
    }

    public void setImgDate(Date imgDate) {
        this.imgDate = imgDate;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
