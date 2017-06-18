package com.bya.springmvc4;

import com.bya.springmvc4.interceptor.DemoInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Created by bao on 2017/6/18.
 * 自己定制mvc的配置
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.bya.springmvc4")
public class MyMvcConfig001 extends WebMvcConfigurerAdapter{

    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/classes/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return  viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 添加静态资源访问路径
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
    }

    // 添加拦截器
    @Bean
    public DemoInterceptor demoInterceptor(){
        return  new DemoInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(demoInterceptor());
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 无业务的页面跳转
        registry.addViewController("/nihao").setViewName("index");
    }

    // 请求路径匹配
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false); // 之前默认url里面.后面的参数会忽略。这样就不会了
    }
}
