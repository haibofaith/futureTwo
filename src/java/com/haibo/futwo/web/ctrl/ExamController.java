package com.haibo.futwo.web.ctrl;

import com.alibaba.fastjson.JSON;
import com.haibo.futwo.web.entity.BaseResponse;
import com.haibo.futwo.web.model.ExamJson;
import com.haibo.futwo.web.model.ExamProperties;
import com.haibo.futwo.web.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author:haibo.xiong
 * @date:2019/2/26
 * @description:
 */
@Controller
@RequestMapping(value = "/exam")
public class ExamController {
    @Autowired
    ExamProperties examProperties;

    @RequestMapping(value = "/login")
    @ResponseBody
    public String helloKitty(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        BaseResponse response = new BaseResponse();
        Person person = new Person();
        if (examProperties.getUsername().equals(username)&&examProperties.getPassword().equals(password)){
            response.setRetcode(0);
            person.setName(username);
            person.setPassword(password);
            response.setBody(person);
            response.setRetdesc("login success");
        }else {
            response.setRetcode(-99);
            response.setRetdesc("用户名或者密码错误");
        }
        return response.toString();
    }


    @RequestMapping(value = "/getMaxOrderNoPerson")
    @ResponseBody
    public String getMaxOrderNoPerson(HttpServletRequest request) {
        BaseResponse response = new BaseResponse();
        final ArrayList<ExamJson> examJsons = (ArrayList<ExamJson>) JSON.parseArray(examProperties.getJson(),ExamJson.class);
        Collections.sort(examJsons, new Comparator<ExamJson>() {
            @Override
            public int compare(ExamJson o1, ExamJson o2) {
                return o2.getOrderno()-o1.getOrderno();
            }
        });
        if (examJsons.get(0)!=null){
            response.setBody(examJsons.get(0).getName());
            response.setRetdesc("找到orderNo值最大的人");
        }
        return response.toString();
    }
}
