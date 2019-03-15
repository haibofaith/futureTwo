package com.haibo.futwo.web.ctrl;

import com.haibo.futwo.web.constant.WeixinConstant;
import com.haibo.futwo.web.entity.BaseResponse;
import com.haibo.futwo.web.model.WeixinRelation;
import com.haibo.futwo.web.model.WeixinSession;
import com.haibo.futwo.web.model.WeixinUser;
import com.haibo.futwo.web.service.FileService;
import com.haibo.futwo.web.service.RelationService;
import com.haibo.futwo.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author:haibo.xiong
 * @date:2019/3/7
 * @description:
 */

@Controller
@RequestMapping(value = "/r")
public class RelationController {
    @Autowired
    private FileService fileService;
    @Autowired
    private UserService userService;
    @Autowired
    private RelationService relationService;
    @RequestMapping("/setRelation")
    @ResponseBody
    public String setRelation(HttpServletRequest request) {
        //1、通过uuid拿到自己的openid
//        String userUuid = request.getParameter("uuid");

        //2、拿到自己的信息
//        WeixinSession userSession =userService.getUserSessionByUuid(userUuid);
        WeixinSession userSession = (WeixinSession) request.getAttribute(WeixinConstant.weixinUser.toString());
        String userOpenid = userSession.getOpenid();
        String userNickName = userSession.getNickName();
        //3、通过昵称查询对方信息
        String friendNickName = request.getParameter("nickName");
        WeixinSession friendSession =userService.getFriendSessionByNickName(friendNickName);
        String friendOpenid = friendSession.getOpenid();
        //4、建立关系weixin_relation/String openid, String nickName, String friendOpenid, String friendNickName
        WeixinRelation weixinRelation = new WeixinRelation(userOpenid,userNickName,friendOpenid,friendNickName);
        int i = relationService.setRelation(weixinRelation);

        BaseResponse response = new BaseResponse();
        if (i>0){
            response.setRetcode(0);
//            response.setBody(weixinUsers.get(0));
            response.setRetdesc("建立关系成功");
        }else {
            response.setRetcode(-99);
            response.setRetdesc("建立关系失败");
        }
        return response.toString();
    }



}
