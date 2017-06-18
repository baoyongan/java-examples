package com.bya.springmvc4.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by bao on 2017/6/18.
 */
@Controller
public class HelloController {

    @RequestMapping("/index")
    public String hello(){
        return  "index";
    }
}
