package com.haibo.futwo.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CrossFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String origin = req.getHeader("Origin");
        if (!org.springframework.util.StringUtils.isEmpty(origin)) {
            //带cookie的时候，origin必须是全匹配，不能使用*
            response.addHeader("Access-Control-Allow-Origin", origin);
//            response.addHeader("Access-Control-Allow-Origin", "*");
        }
        response.addHeader("Access-Control-Allow-Methods", "*");

        String headers = req.getHeader("Access-Control-Request-Headers");

        // 支持所有自定义头
        if (!org.springframework.util.StringUtils.isEmpty(headers)) {
            response.addHeader("Access-Control-Allow-Headers", headers);
        }
        response.addHeader("Access-Control-Max-Age", "0");

        // enable cookie 支持cookie
        response.addHeader("Access-Control-Allow-Credentials", "true");
        filterChain.doFilter(servletRequest,response);
    }

    @Override
    public void destroy() {

    }
}
