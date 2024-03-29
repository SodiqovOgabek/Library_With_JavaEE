package com.company.library_javaEE.servlets.handlers;


import com.company.library_javaEE.dto.ErrorDto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/400")
public class InvalidInputExceptionHandler extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Throwable throwable = (Throwable) req.getAttribute("javax.servlet.error.exception");
        String message = throwable.getMessage();
        String requestURI = req.getRequestURI();
        ErrorDto error = ErrorDto.builder()
                .message(message)
                .path(requestURI)
                .build();
        req.setAttribute("error", error);
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/errors/404.jsp");
        dispatcher.forward(req, resp);
    }
}
