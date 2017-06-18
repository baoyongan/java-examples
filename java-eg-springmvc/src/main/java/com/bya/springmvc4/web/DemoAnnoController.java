package com.bya.springmvc4.web;

import com.bya.springmvc4.domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by bao on 2017/6/18.
 */
@Controller
@RequestMapping("/anno")
public class DemoAnnoController {

    @RequestMapping(produces = "text/plain;charset=UTF-8") // 没有路径 默认就是类的“/anno”,produce可定制返回的response的媒体类型和字符集
    public @ResponseBody String index(HttpServletRequest request){
        return  "url:"+request.getRequestURL()+"can access";
    }

    @RequestMapping(value = "/pathvar/{str}",produces = "text/plain;charset=UTF-8") //接受路径参数
    public @ResponseBody String demoPathVar(@PathVariable String str,HttpServletRequest request){
        return  "url:"+request.getRequestURL()+"can access,str:"+str;
    }

    @RequestMapping(value = "/requestParam",produces = "text/plain;charset=UTF-8") // 常规的接受参数 /anno/requestParam?id=
    public @ResponseBody String demoPathVar(Long id,HttpServletRequest request){
        return  "url:"+request.getRequestURL()+"can access,:id"+id;
    }

    @RequestMapping(value = "/obj",produces = "application/json;charset=UTF-8") // 解释参数到对象 /anno/obj?id=1&name=
    @ResponseBody
    public String passObj(DemoObj obj,HttpServletRequest request){
        return  "url:"+request.getRequestURL()+"can access,obj id:"+obj.getId()+"obj name:"+obj.getName();
    }

    @RequestMapping(value = {"/name1","/name2"},produces = "text/plain;charset=UTF-8") // 映射不同的路劲到相同的方法
    public  @ResponseBody String remove(HttpServletRequest request){
        return  "url:"+request.getRequestURL()+"can access";
    }
}
