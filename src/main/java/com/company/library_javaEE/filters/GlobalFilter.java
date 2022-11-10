package com.company.library_javaEE.filters;

import com.company.library_javaEE.configs.ApplicationContextHolder;
import com.company.library_javaEE.service.auth.UserService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class GlobalFilter implements Filter {
    private final List<String> WHITE_LIST = new ArrayList<>(Arrays.asList(
            "/login",
            "/register"
    ));
    private final UserService userService = ApplicationContextHolder.getBean(UserService.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (WHITE_LIST.contains(req.getRequestURI())) {
            chain.doFilter(req, response);
            return;
        }
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("session_user")) {
                    String emailAddress = cookie.getValue();
                    req.setAttribute("user", userService.getByEmail(emailAddress));
                    chain.doFilter(req, response);
                    return;
                }
            }
        }
        ((HttpServletResponse) response).sendRedirect("/login");
    }



}
