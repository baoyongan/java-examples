package com.bya.web.app1.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by bao on 2017/6/18.
 */
@Controller
public class HelloController {

    @RequestMapping("/app1")
    public String hello(){
        System.out.println("app1"+System.currentTimeMillis());
        return "app1";
    }

    @RequestMapping(value = "/pathvar/{str}",produces = "text/plain;charset=UTF-8") //接受路径参数
    public @ResponseBody
    String demoPathVar(@PathVariable String str, HttpServletRequest request){
        return  "url:"+request.getRequestURL()+"can access,str:"+str;
    }

    @RequestMapping(value = "/obj",produces = "application/json;charset=UTF-8") // 解释参数到对象 /anno/obj?id=1&name=
    @ResponseBody
    public String passObj(DemoObj obj,HttpServletRequest request){
        return  "url:"+request.getRequestURL()+"can access,obj id:"+obj.getId()+"obj name:"+obj.getName();
    }

    @RequestMapping("/advice")
    public String getSomething(@ModelAttribute("msg") String msg, DemoObj obj){
        throw new IllegalArgumentException("非常抱歉，参数有误/"+"来自@ModelAttribute："+msg);
    }
}
