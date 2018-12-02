package com.frame.ssm.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PermissionsFilter implements Filter {

    private static final Logger logger = Logger.getLogger(PermissionsFilter.class);


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String basePath = req.getContextPath();

        logger.info("请求路径:"+basePath);

        // 得到页面的访问路径
        String path = req.getServletPath();
        logger.info("访问路径:"+path);
        String requestType = req.getHeader("X-Requested-With");
        logger.info(requestType);
        logger.info(req.getSession().getId());

        if (path == null) {// 如果没有路径，跳到登录页面
            res.sendRedirect(basePath + "/login.jsp");
            return;
        }
        if (path.equals("/login.jsp")
            || path.equals("/listener/init.do")
                || path.equals("/interceptor/init.do")
        ){// 不过滤的页面
            chain.doFilter(request, response);
            return;
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("PermissionsFilter初始化...");
    }

    @Override
    public void destroy() {
        System.out.println("PermissionsFilter结束...");
    }
}
