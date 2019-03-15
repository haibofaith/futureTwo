package com.haibo.futwo.web.interceptors;

import com.haibo.futwo.web.constant.WeixinConstant;
import com.haibo.futwo.web.model.WeixinSession;
import com.haibo.futwo.web.service.UserService;
import com.haibo.futwo.web.utils.JedisUtil;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Resource
    private JedisUtil jedisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1、通过uuid拿到自己的openid
        String userUuid = request.getParameter("uuid");
        WeixinSession userSession = null;
        if (userUuid != null) {
            userSession = jedisUtil.geto(userUuid, WeixinSession.class);

            if (userSession != null) {
                //有缓存
            } else {
                //无缓存
                //2、拿到自己的信息
                userSession = userService.getUserSessionByUuid(userUuid);
//            String userOpenid = userSession.getOpenid();
//            String userNickName = userSession.getNickName();
                jedisUtil.seto(userUuid, userSession);
            }
        }else {
            //无缓存
            //2、拿到自己的信息
//            userSession = userService.getUserSessionByUuid(userUuid);
//            String userOpenid = userSession.getOpenid();
//            String userNickName = userSession.getNickName();
//            jedisUtil.seto(userUuid, userSession);
        }
        request.setAttribute(WeixinConstant.weixinUser.toString(), userSession);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle run!");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion run!");
    }
}
