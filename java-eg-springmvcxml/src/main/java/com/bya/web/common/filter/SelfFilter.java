package com.bya.web.common.filter;

import javax.servlet.*;
import java.io.IOException;

public class SelfFilter implements Filter{
    private String owner;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("######SelfFilter INIT");
        owner=filterConfig.getInitParameter("filterOwner");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("######Before DoFilter SelfFilter");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("######After DoFilter SelfFilter");
    }

    @Override
    public void destroy() {
        System.out.println("######SelfFilter DESTROY");
    }
}
