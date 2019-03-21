package com.bya.java.eg.web.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

//@WebServlet(name = "LoginServlet",urlPatterns = {"login"})
public class LoginServlet extends HttpServlet {

    private Logger log= LoggerFactory.getLogger(LoginServlet.class);

    private Object og=new Object();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("name");
        String password=request.getParameter("password");

        log.info("login request name:{}",name);
        // todo login

        request.getSession().setAttribute("user",name);
        HttpSession session=request.getSession();
        long now;
        synchronized (og){
          AtomicLong times= (AtomicLong) session.getAttribute("times");
          if(null==times){
              times=new AtomicLong(1);
              now=1;
          }
          else{
              now=times.incrementAndGet();
          }
            session.setAttribute("times",times);
        }
        System.out.println(session.getId()+" TIMES "+now);
        response.sendRedirect(response.encodeURL("welcome.jsp")); // 自动判断是否禁用cookie加上sessionid
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
