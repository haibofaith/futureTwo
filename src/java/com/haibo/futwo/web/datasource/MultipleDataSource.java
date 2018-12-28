package com.haibo.futwo.web.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/** 多数据源java实现 */
public class MultipleDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        String dsName = DynamicDataSourceHolder.getDataSourceName();
        return dsName;
    }
}
