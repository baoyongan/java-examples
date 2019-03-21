package com.bya.web.app2.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HandlerController {


    @RequestMapping("/rd1")
   public String redirct1(){
       return "redirect:http://www.baidu.com";
   }

    @RequestMapping("/rd2")
    public ModelAndView redirct2(){
        ModelAndView view=new ModelAndView();
        view.setView(new RedirectView("http://www.baidu.com"));
        view.getModel().put("baidu","hello");
        return view;
    }

    @RequestMapping("/login")
    public String login(@RequestParam String name,HttpServletRequest request){
        System.out.println(name);
        request.setAttribute("name",name);
        return "forward:secode";
    }

    @RequestMapping("/secode")
    public String secode(@RequestParam String name,HttpServletRequest request){
        System.out.println("---"+name);
        return "form2";
    }






}