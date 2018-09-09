package com.bya.web.app1.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
