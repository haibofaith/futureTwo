package com.haibo.futwo.web.mappers;

import com.haibo.futwo.web.model.WeixinSession;
import com.haibo.futwo.web.model.WeixinUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author:haibo.xiong
 * @date:2019/3/3
 * @description:
 */
@Repository
public interface UserMapper {
    List<WeixinUser> login(@Param("username")String username, @Param("password") String password);

    int onLogin(WeixinSession session);

    String getOpenIdByUuid(@Param("uuid") String uuid);

    WeixinSession getFriendSessionByNickName(@Param("nickName") String nickName);

    WeixinSession getUserSessionByUuid(@Param("uuid") String uuid);

    List<WeixinSession> searchUsersByNickName(@Param("nickName") String nickName);
}
