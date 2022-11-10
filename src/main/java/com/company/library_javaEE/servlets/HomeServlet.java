package com.company.library_javaEE.servlets;

import com.company.library_javaEE.configs.ApplicationContextHolder;
import com.company.library_javaEE.domains.UserInfo;
import com.company.library_javaEE.enums.Genre;
import com.company.library_javaEE.enums.Language;
import com.company.library_javaEE.service.book.BookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


@WebServlet("")
public class HomeServlet extends HttpServlet {
    BookService bookService = ApplicationContextHolder.getBean(BookService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = Integer.parseInt(Optional.ofNullable(req.getParameter("page")).orElse("1"));
        int limit = Integer.parseInt(Optional.ofNullable(req.getParameter("limit")).orElse("12"));
        String search = Optional.ofNullable(req.getParameter("search")).orElse("");

        req.setAttribute("books", bookService.getAll(search, page, limit));
        req.setAttribute("pageInfo", UserInfo.builder()
                .hasPrevious(bookService.hasPrevious(page))
                .hasNext(bookService.hasNext(search, page, limit))
                .number(page)
                .totalPages(bookService.totalPage(search, limit))
                .build());
        req.setAttribute("search", search);
        req.setAttribute("genres", Genre.values());
        req.setAttribute("languages", Language.values());
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/main.jsp");
        dispatcher.forward(req, resp);

    }
}