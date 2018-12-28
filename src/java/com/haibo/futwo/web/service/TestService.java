package com.haibo.futwo.web.service;


import com.haibo.futwo.web.datasource.DataSource;
import com.haibo.futwo.web.datasource.DataSourceConstant;

public interface TestService {

    @DataSource(DataSourceConstant.DATA_SOURCE_ORACLE)
    Integer getSumSalary();

}
