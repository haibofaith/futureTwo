package com.haibo.futwo.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author:haibo.xiong
 * @date:2019/3/14
 * @description: CREATE TABLE `weixin_book` (
 * `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
 * `book_title` varchar(50) NOT NULL DEFAULT '' COMMENT 'user_openid',
 * `_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
 * PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='小程序书籍目录表';
 */
public class WeixinBook implements Serializable {
    private Long id;
    private String bookTitle;
    private Long bookId;
    private String bookPath;

    public String getBookPath() {
        return bookPath;
    }

    public void setBookPath(String bookPath) {
        this.bookPath = bookPath;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    private Date timestamp;

    public WeixinBook() {
    }

    public WeixinBook(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public WeixinBook(String bookTitle, Long bookId) {
        this.bookTitle = bookTitle;
        this.bookId = bookId;
    }

    public WeixinBook(String bookTitle, Long bookId, String bookPath) {
        this.bookTitle = bookTitle;
        this.bookId = bookId;
        this.bookPath = bookPath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

}
