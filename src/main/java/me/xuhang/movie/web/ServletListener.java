package me.xuhang.movie.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import me.xuhang.movie.utils.ApplicationUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 * Created by xuhang on 2019/11/20.
 */

@WebListener
public class ServletListener implements ServletContextListener {

    @Autowired
    private ApplicationUtils applicationUtils;


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        WebApplicationContextUtils
                .getRequiredWebApplicationContext(servletContextEvent.getServletContext())
                .getAutowireCapableBeanFactory()
                .autowireBean(this);

        ServletContext context = servletContextEvent.getServletContext();
        context.setAttribute("appName", "XH-Movie");
        context.setAttribute("douban", "http://movie.douban.com/subject/");
        context.setAttribute("appUtils",applicationUtils);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
