package com.haibo.futwo.web.ctrl;

import com.alibaba.fastjson.JSONArray;
import com.haibo.futwo.web.client.GetDatabaseInfo;
import com.haibo.futwo.web.client.MobileCodeWS;
import com.haibo.futwo.web.client.ObjectFactory;
import com.haibo.futwo.web.constant.WeixinConstant;
import com.haibo.futwo.web.mappers.ITestUserMapper;
import com.haibo.futwo.web.model.BaseResponse;
import com.haibo.futwo.web.model.Person;
import com.haibo.futwo.web.model.User;
import com.haibo.futwo.web.model.WeixinSession;
import com.haibo.futwo.web.service.TestService;
import com.haibo.futwo.web.utils.JedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Api(value = "测试API", description = "测试API")
@Controller
@RequestMapping(value = "/a")
public class HelloCtrl {
    Logger logger = LoggerFactory.getLogger(HelloCtrl.class);
    @RequestMapping(value = "/helloDog")
    @ResponseBody
    public String helloKitty(HttpServletRequest request) {
        String callback = request.getParameter("callbackparm");
        String resp = callback + "([ { name:\"dog\"}])";
        System.out.println("--------"+System.getProperties().getProperty("user.dir"));
        System.out.println("--------"+System.getProperties().getProperty("user.home"));
        return resp;
    }

    @Resource
    private JedisUtil jedisUtil;
    @RequestMapping(value = "/setRedis")
    @ResponseBody
    public String setRedis(HttpServletRequest request){
        String key = request.getParameter("key");
        String value = request.getParameter("value");
        logger.debug("key"+key+" value"+value);
        BaseResponse response = new BaseResponse();
        response.setBody("key:"+key+" value:"+value);
        jedisUtil.sets(key,value);
        return response.toString();
    }

    @RequestMapping(value = "/getRedisValueByKey")
    @ResponseBody
    public String getRedisValueByKey(HttpServletRequest request){
        String key = request.getParameter("key");
        BaseResponse response = new BaseResponse();
        String value = jedisUtil.gets(key);
        response.setBody("key:"+key+" value:"+value);
//        jedisUtil.sets(key,value);
        return response.toString();
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
        WeixinSession weixinSession = (WeixinSession) request.getAttribute(WeixinConstant.weixinUser.toString());
        baseResponse.setBody(name.toString()+weixinSession.getNickName());
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

    @RequestMapping(value = "/hellofree")
    public ModelAndView helloFreemarker(HttpServletRequest request) throws ParseException {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("freemarker/hellofree");
//        mv.addObject("username","你好，Freemarker");
        List<User> people = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2017-11-18 12:30:15");
        people.add(new User("bobo",date));
        people.add(new User("hehe",new Date()));
        mv.addObject("userList",people);

        List<String> mylist = new ArrayList<>();
        mylist.add("衣服");
        mylist.add("裤子");
        mylist.add("领带");
        mylist.add("帽子");
        mylist.add("袜子");
        mylist.add("腰带");
        String jsArr = JSONArray.toJSONString(mylist);
        mv.addObject("mylist",jsArr);

        mv.addObject("title","农商行测试用表格");

        BaseResponse response = new BaseResponse();
        response.setBody(mylist);
        System.out.println(response.toString());
        mv.addObject("mylist2",response.toString());

        return mv;
    }

    @RequestMapping(value = "/webservice")
    @ResponseBody
    public String webservice(HttpServletRequest request) {
        String phone = request.getParameter("phone");
        MobileCodeWS mobileCodeWS = new MobileCodeWS();
        String resp = mobileCodeWS.getMobileCodeWSSoap().getMobileCodeInfo(phone,"");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setBody(resp);
        return baseResponse.toString();
    }

    @RequestMapping(value = "/helloHtml")
    public ModelAndView helloHtml(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("freemarker/demo1");
        return mv;
    }

}
