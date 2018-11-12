package com.haibo.futwo.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CrossFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:8082");
        response.addHeader("Access-Control-Allow-Methods", "*");
        filterChain.doFilter(servletRequest,response);
    }

    @Override
    public void destroy() {

    }
}
