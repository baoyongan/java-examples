package com.bya.springmvc4;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by bao on 2017/6/18.
 * WebApplicationInitializer 接口是用来配置Servlet3.0+配置的接口，从而实现了替代web.xml的位置。
 */
public class WebInitializer implements WebApplicationInitializer{

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // 配置类
        AnnotationConfigWebApplicationContext ctx=new AnnotationConfigWebApplicationContext();
        // ctx.register(MyMvcConfig.class); // 启用默认配置
        ctx.register(MyMvcConfig001.class); // 定制mvc基本配置
        ctx.setServletContext(servletContext);

        // 注册Dispatcher Servlet
        ServletRegistration.Dynamic servlet=servletContext.addServlet("dispatcher",new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);

    }
}
