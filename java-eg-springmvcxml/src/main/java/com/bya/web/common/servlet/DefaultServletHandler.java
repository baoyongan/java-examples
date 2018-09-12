package com.bya.web.common.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DefaultServletHandler extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("####默认的servlet 处理开始");
        resp.getWriter().write("404 NOT FOUND");
        System.out.println("####默认的servlet 处理完毕");
    }
}
