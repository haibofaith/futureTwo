package com.haibo.futwo.web.ctrl;

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
        return "kittyDog";
    }
}
