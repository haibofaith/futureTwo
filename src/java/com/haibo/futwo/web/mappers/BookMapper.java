package com.haibo.futwo.web.mappers;

import com.haibo.futwo.web.model.WeixinBook;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookMapper {
    int insertBook(WeixinBook weixinBook);
    List<WeixinBook> selectBookByPage(@Param("start") int start,@Param("limit") int limit);
}
