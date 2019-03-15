package com.haibo.futwo.web.service.impl;

import com.haibo.futwo.web.mappers.BookMapper;
import com.haibo.futwo.web.model.FileProperties;
import com.haibo.futwo.web.model.WeixinBook;
import com.haibo.futwo.web.reptiles.MyBookCrawler;
import com.haibo.futwo.web.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:haibo.xiong
 * @date:2019/3/14
 * @description:
 */
@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private FileProperties fileProperties;
    @Override
    public int insertBook(WeixinBook weixinBook) {
        return bookMapper.insertBook(weixinBook);
    }

    @Override
    public int insertBooks() {
        //后台拉取数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                MyBookCrawler crawler = new MyBookCrawler("crawl", true,bookMapper,fileProperties);
                try {
                    crawler.start(4);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return 0;
    }


    @Override
    public List<WeixinBook> selectBookByPage(int pageNo, int pageSize) {
        int start = (pageNo-1)*pageSize;
        int limit = pageSize;
        return bookMapper.selectBookByPage(start,limit);
    }
}
