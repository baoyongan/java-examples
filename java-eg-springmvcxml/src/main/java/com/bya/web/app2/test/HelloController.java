package com.bya.web.app2.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bao on 2017/6/18.
 */
@Controller
public class HelloController {

    @RequestMapping("/app2")
    public String hello(){

        System.out.println("app2"+System.currentTimeMillis());

        return "app2";
    }


}
