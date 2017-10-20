package com.bya.springmvc4.web;

import com.bya.springmvc4.domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by bao on 2017/6/18.
 */
@Controller
public class XssController {

    @RequestMapping("/xxs01")
    public String xss(HttpServletRequest request) {
        String name = request.getParameter("name");
        request.getSession().setAttribute("name", "<script>alert(1)</script>");
        return "xss01";
    }
}


