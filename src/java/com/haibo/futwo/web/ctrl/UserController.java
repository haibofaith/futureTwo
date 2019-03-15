package com.haibo.futwo.web.ctrl;

import com.alibaba.fastjson.JSONObject;
import com.haibo.futwo.web.entity.BaseResponse;
import com.haibo.futwo.web.model.SimWeixinSession;
import com.haibo.futwo.web.model.WeixinSession;
import com.haibo.futwo.web.model.WeixinUser;
import com.haibo.futwo.web.service.UserService;
import com.haibo.futwo.web.utils.JedisUtil;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author:haibo.xiong
 * @date:2019/3/2
 * @description:
 */

@Controller
@RequestMapping(value = "/c")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        BaseResponse response = new BaseResponse();
        List<WeixinUser> weixinUsers = userService.login(username,password);
        if (weixinUsers.size()>0){
            response.setRetcode(0);
            response.setBody(weixinUsers.get(0));
            response.setRetdesc("login success");
        }else {
            response.setRetcode(-99);
            response.setRetdesc("用户名或者密码错误");
        }
        return response.toString();
    }
    @Resource
    private JedisUtil jedisUtil;

    @RequestMapping("/onLogin")
    @ResponseBody
    public String onLogin(HttpServletRequest httpServletRequest) {
        String JSCODE = httpServletRequest.getParameter("code");
        String nickName =httpServletRequest.getParameter("nickName");
        String avatarUrl =httpServletRequest.getParameter("avatarUrl");
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String APPID = "wx16b3529d19922efb";
        String SECRET = "754d398507b3a7b49fba3437f4fbb3e0";
        WeixinSession weixinSession  = null;
        try {
            // 创建httpget.
            HttpGet httpget = new HttpGet("https://api.weixin.qq.com/sns/jscode2session?appid="+APPID+"&secret="+SECRET+"&js_code="+JSCODE+"&grant_type=authorization_code");
            System.out.println("executing request " + httpget.getURI());
            // 执行get请求.
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                System.out.println("------output begin---------");
                // 打印响应状态
                System.out.println(response.getStatusLine());
                if (entity != null) {
                    // 打印响应内容长度
                    System.out.println("Response content length: " + entity.getContentLength());
                    String resBody = EntityUtils.toString(entity);
                    JSONObject json = JSONObject.parseObject(resBody);
                    weixinSession  = JSONObject.toJavaObject(json,WeixinSession.class);
                    final String uuid = UUID.randomUUID().toString();
                    weixinSession.setUuid(uuid);
                    weixinSession.setNickName(nickName);
                    weixinSession.setAvatarUrl(avatarUrl);
                    int i = userService.onLogin(weixinSession);
                    // 打印响应内容
                    System.out.println("Response content: " + weixinSession.toString());
                }
                System.out.println("------output end-----------");
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BaseResponse response = new BaseResponse();
        response.setBody(weixinSession.getUuid());
//        WeixinSession userSession = userService.getUserSessionByUuid(weixinSession.getUuid());
//        jedisUtil.seto(weixinSession.getUuid(), userSession);
        return response.toString();
    }

    @RequestMapping("/searchUsersByNickName")
    @ResponseBody
    public String searchUsersByNickName(HttpServletRequest httpServletRequest){
        String nickName =httpServletRequest.getParameter("nickName");
        BaseResponse response = new BaseResponse();
        List<WeixinSession> sessions = userService.searchUsersByNickName(nickName);
        List<SimWeixinSession> simWeixinSessions = new ArrayList<>();
        for (WeixinSession weixinSession:sessions){
            //String uuid, String nickName, String avatarUrl
            simWeixinSessions.add(new SimWeixinSession(weixinSession.getUuid(),weixinSession.getNickName(),weixinSession.getAvatarUrl()));
        }
        response.setBody(simWeixinSessions);
        return response.toString();
    }



}
