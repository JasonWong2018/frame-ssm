package com.frame.ssm.filter;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class PermissionsListener implements ServletContextListener {
    // 应用监听器的销毁方法
    public void contextDestroyed(ServletContextEvent event) {
        ServletContext sc = event.getServletContext();
        // 在整个web应用销毁之前调用，将所有应用空间所设置的内容清空
        sc.removeAttribute("initData");
        System.out.println("销毁工作完成...");
    }

    // 应用监听器的初始化方法
    public void contextInitialized(ServletContextEvent event) {
        // 通过这个事件可以获取整个应用的空间
        // 在整个web应用下面启动的时候做一些初始化的内容添加工作
        ServletContext sc = event.getServletContext();
        // 设置一些基本的内容；比如一些参数或者是一些固定的对象
        sc.setAttribute("initData", "initData");
        System.out.println("应用监听器初始化工作完成...");
        System.out.println("已经创建initData...");
    }

}
