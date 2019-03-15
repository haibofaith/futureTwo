package com.haibo.futwo.web.ctrl;

import com.haibo.futwo.web.entity.BaseResponse;
import com.haibo.futwo.web.model.VoteInfo;
import com.haibo.futwo.web.model.WeixinSession;
import com.haibo.futwo.web.service.UserService;
import com.haibo.futwo.web.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author:haibo.xiong
 * @date:2019/3/8
 * @description:
 */
@Controller
@RequestMapping(value = "/v")
public class VoteController {
    @Autowired
    private UserService userService;

    @Autowired
    private VoteService voteService;

    @RequestMapping("/newVote")
    @ResponseBody
    public String newVote(HttpServletRequest request) {
        //1、通过uuid拿到自己的openid
        String uuid = request.getParameter("uuid");
        String voteName = request.getParameter("voteName");
        WeixinSession userSession =userService.getUserSessionByUuid(uuid);
        //2、新建投票
        VoteInfo voteInfo = new VoteInfo();
        voteInfo.setOpenId(userSession.getOpenid());
        voteInfo.setNickName(userSession.getNickName());
        voteInfo.setVoteName(voteName);
        int i = voteService.newVote(voteInfo);
        BaseResponse response = new BaseResponse();
        if (i>0){
            response.setRetdesc("新建投票成功");
        }else {
            response.setRetdesc("新建投票失败");
        }
        return response.toString();
    }

    @RequestMapping("/addVoteCondition")
    @ResponseBody
    public String addVoteCondition(HttpServletRequest request) {
        //1、通过uuid拿到自己的openid
        String uuid = request.getParameter("uuid");
        //2、新建投票id
        String voteId= request.getParameter("voteId");
        //3、单选多选
        String voteType = request.getParameter("voteType");
        String voteConditions = request.getParameter("voteConditions");
        WeixinSession userSession =userService.getUserSessionByUuid(uuid);
        String[] voteConditionArray = voteConditions.split(",");
        //todo 未写完
        for (String voteCondition:voteConditionArray){

        }
        BaseResponse response = new BaseResponse();
        return response.toString();
    }


}
