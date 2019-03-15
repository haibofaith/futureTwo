package com.haibo.futwo.web.service;

import com.haibo.futwo.web.datasource.DataSource;
import com.haibo.futwo.web.datasource.DataSourceConstant;
import com.haibo.futwo.web.model.WeixinImg;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;


public interface FileService {
    @DataSource(DataSourceConstant.DATA_SOURCE_LOCAL)
    int uploadImg(WeixinImg weixinImg);
    @DataSource(DataSourceConstant.DATA_SOURCE_LOCAL)
    List<String> selectImgParentDistinct(String uuid);
    @DataSource(DataSourceConstant.DATA_SOURCE_LOCAL)
    List<WeixinImg> selectImgByImgFile(String imgFile,String uuid);
    @DataSource(DataSourceConstant.DATA_SOURCE_LOCAL)
    List<WeixinImg> selectFriendImgByImgFile(String imgFile,String uuid,String friendOpenid);

}
