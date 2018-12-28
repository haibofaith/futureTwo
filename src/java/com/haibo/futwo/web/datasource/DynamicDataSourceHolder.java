package com.haibo.futwo.web.datasource;

public class DynamicDataSourceHolder {

    public static final ThreadLocal<String> holder = new ThreadLocal<String>();

    public static void putDataSource(String dataSourceName) {
        holder.set(dataSourceName);
    }

    public static String getDataSourceName() {
        return holder.get();
    }
}
