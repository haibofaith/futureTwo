package com.haibo.futwo.web.ctrl;

import com.haibo.futwo.web.model.BaseResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/a")
public class HelloCtrl {
    @RequestMapping(value = "/helloDog")
    @ResponseBody
    public String helloKitty(HttpServletRequest request) {
        String callback = request.getParameter("callbackparm");
        String resp = callback + "([ { name:\"John\"}])";
        return resp;
    }
}
