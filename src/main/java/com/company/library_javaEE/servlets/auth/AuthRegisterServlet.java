package com.company.library_javaEE.servlets.auth;


import com.company.library_javaEE.configs.ApplicationContextHolder;
import com.company.library_javaEE.dto.auth.UserCreateDTO;
import com.company.library_javaEE.dto.auth.UserDTO;
import com.company.library_javaEE.service.auth.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/register")
public class AuthRegisterServlet extends HttpServlet {

    private final UserService userService = ApplicationContextHolder.getBean(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/auth/register.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        UserCreateDTO userCreateDTO = UserCreateDTO.builder()
                .email(email)
                .password(password)
                .surname(surname)
                .name(name)
                .build();


        UserDTO userDTO = userService.create(userCreateDTO);
        Cookie cookie = new Cookie("session_user", userDTO.getEmail());
        resp.addCookie(cookie);
        resp.sendRedirect("/");
    }
}
