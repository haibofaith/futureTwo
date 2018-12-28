package com.haibo.futwo.web.ctrl;

import com.haibo.futwo.web.mappers.ITestUserMapper;
import com.haibo.futwo.web.model.BaseResponse;
import com.haibo.futwo.web.model.Person;
import com.haibo.futwo.web.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/a")
public class HelloCtrl {
    @RequestMapping(value = "/helloDog")
    @ResponseBody
    public String helloKitty(HttpServletRequest request) {
        String callback = request.getParameter("callbackparm");
        String resp = callback + "([ { name:\"dog\"}])";
        return resp;
    }

    @RequestMapping(value = "/completeReq")
    @ResponseBody
    public BaseResponse completeReq(@RequestBody Person person) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setBody(person.toString());
        return baseResponse;
    }

    @RequestMapping(value = "/setCookie")
    @ResponseBody
    public String setCookie(HttpServletRequest request, @RequestBody Person person, HttpServletResponse response) {
        Cookie cookie = new Cookie(request.getServerName(), person.getName());//创建一个cookie，cookie的名字是lastAccessTime
        cookie.setMaxAge(30 * 24 * 60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "success";
    }


    @RequestMapping(value = "/cookieReq")
    @ResponseBody
    public BaseResponse cookieReq(HttpServletRequest request) {
        String domain = request.getServerName();
        //1、cookie中获得name
        String name = getSessionId(request, domain);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setBody(name.toString());
        return baseResponse;
    }

    /**
     * 从request中获取sessionId
     *
     * @param request
     * @return
     */
    public String getSessionId(HttpServletRequest request, String domain) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return null;
        }
        String sessionId = "";
        for (int i = 0; i < cookies.length; i++) {
            Cookie c = cookies[i];
            if (c.getName().equalsIgnoreCase(domain)) {
                sessionId = c.getValue();
                break;
            }
        }
        return sessionId;
    }

    @RequestMapping(value = "/getHeader")
    @ResponseBody
    public BaseResponse getHeader(HttpServletRequest request) {
        String header = request.getHeader("x-header1");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setBody(header);
        return baseResponse;
    }

    @Resource
    private ITestUserMapper testUserMapper;

    @RequestMapping(value = "/dockerMysql")
    @ResponseBody
    public BaseResponse dockerMysql(){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setBody(testUserMapper.selectAllUsers());
        return baseResponse;
    }

    @Autowired
    private TestService testService;

    @RequestMapping("/oracle")
    @ResponseBody
    public String oracle(HttpServletRequest httpServletRequest) {
        int i = testService.getSumSalary();
        return "hello oracle:"+i;
    }

}
