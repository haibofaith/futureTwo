package com.haibo.futwo.web.ctrl;

import com.haibo.futwo.web.constant.WeixinConstant;
import com.haibo.futwo.web.entity.BaseResponse;
import com.haibo.futwo.web.model.Question;
import com.haibo.futwo.web.model.WeixinSession;
import com.haibo.futwo.web.service.QuestionService;
import com.haibo.futwo.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author:haibo.xiong
 * @date:2019/3/8
 * @description:
 */
@Controller
@RequestMapping(value = "/q")
public class QuestionnaireController {
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;

    @RequestMapping("/setQuestion")
    @ResponseBody
    public String setQuestion(HttpServletRequest request) {
        //1、通过uuid拿到自己的openid
//        String userUuid = request.getParameter("uuid");
        //2、拿到自己的信息
//        WeixinSession userSession =userService.getUserSessionByUuid(userUuid);
        WeixinSession userSession = (WeixinSession) request.getAttribute(WeixinConstant.weixinUser.toString());
        String userUuid = userSession.getUuid();
        String userOpenid = userSession.getOpenid();
        String userNickName = userSession.getNickName();
        //3、拿到问题集合
        String qTitle = request.getParameter("qTitle");
        String questions = request.getParameter("questions");
        String[] qAttr = questions.split(",");
        List<Question> qList = new ArrayList<>();
        final String qNum = UUID.randomUUID().toString();
        for (String question : qAttr){
//            String uuid, String openid, String nickName, String qNum, String question
            qList.add(new Question(userUuid,userOpenid,userNickName,qNum,question,qTitle));
        }
        //4、插入到问题集合表
        int i = questionService.insertQuestions(qList);

        BaseResponse response = new BaseResponse();
        if (i>0){
            response.setBody("成功插入"+i+"条数据");
        }else {
            response.setRetcode(-99);
            response.setBody("插入失败");
        }
        return response.toString();
    }

    @RequestMapping("/getQuestionsByQNum")
    @ResponseBody
    public String getQuestionsByQNum(HttpServletRequest request){
        String qNum = request.getParameter("qNum");
        BaseResponse response = new BaseResponse();
        List<Question> questions = questionService.selectQuestionsByQNum(qNum);
        response.setBody(questions);
        return response.toString();
    }
}
