package com.bya.web.app1.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by bao on 2017/6/18.
 */
// 注解参数可以指定特定的 controller
@ControllerAdvice
public class ControllerAdviceDemo {

    private static Logger log= LoggerFactory.getLogger(ControllerAdviceDemo.class);

    @ExceptionHandler(value = Exception.class)
    public ModelAndView exception(Exception exception, WebRequest request){
        log.info("###########ExceptionHandler");
        ModelAndView modelAndView=new ModelAndView("error");
        modelAndView.addObject("errorMessage",exception.getMessage());
        return modelAndView;
    }



    @ModelAttribute
    public void addAttributes(Model model){
        log.info("###########ModelAttribute");
        model.addAttribute("msg","额外信息");
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        log.info("###########InitBinder");
        webDataBinder.setDisallowedFields("id");
    }

}
