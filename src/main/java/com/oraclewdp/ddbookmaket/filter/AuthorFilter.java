package com.oraclewdp.ddbookmaket.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AuthorFilter",urlPatterns = "/*")
public class AuthorFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest) req;
        HttpServletResponse response=(HttpServletResponse)resp;
        System.out.println(request.getRequestURI());
        if (request.getRequestURI().endsWith("/login.jsp")||request.getRequestURI().contains("/bower_components/")||request.getRequestURI().endsWith("/login")||request.getRequestURI().endsWith("vcode.png")){
            chain.doFilter(req,resp);
            return;
        }
        if (request.getSession().getAttribute("hasLongined")==null||!(Boolean)request.getSession().getAttribute("hasLongined")){
            response.sendRedirect("login.jsp");
            return;
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
