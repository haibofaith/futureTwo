package com.haibo.futwo.web.mappers;

import com.haibo.futwo.web.model.WeixinImg;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileMapper {
    int uploadImg(WeixinImg weixinImg);
    List<String> selectImgParentDistinct(@Param("open_id") String open_id);
    List<WeixinImg> selectImgByImgFile(@Param("imgFile") String imgFile,@Param("open_id") String open_id);
}
