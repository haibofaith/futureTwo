package com.haibo.futwo.web.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 并没有用
 */
public class EncodingFilter implements Filter {

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        // 将请求和响应强制转换成Http形式
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        request.setCharacterEncoding("utf-8");
        // 处理响应乱码
        response.setContentType("text/html;charset=UTF-8");

        // 自定义一个request对象：MyRequest，对服务器原来的requset进行增强，使用装饰设计模式
        // 要增强原来的request对象，必须先获取到原来的request对象
        // 注意：放行的时候应该传入增强后的request对象
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}