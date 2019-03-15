package com.haibo.futwo.web.service;

import com.haibo.futwo.web.datasource.DataSource;
import com.haibo.futwo.web.datasource.DataSourceConstant;
import com.haibo.futwo.web.model.WeixinBook;

import java.util.List;

public interface BookService {
    @DataSource(DataSourceConstant.DATA_SOURCE_LOCAL)
    int insertBook(WeixinBook weixinBook);
    @DataSource(DataSourceConstant.DATA_SOURCE_LOCAL)
    int insertBooks();
    @DataSource(DataSourceConstant.DATA_SOURCE_LOCAL)
    List<WeixinBook> selectBookByPage(int pageNo, int pageSize);
}
