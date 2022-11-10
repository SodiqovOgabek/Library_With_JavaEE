package com.company.library_javaEE.servlets;



import com.company.library_javaEE.configs.ApplicationContextHolder;
import com.company.library_javaEE.domains.Book;
import com.company.library_javaEE.domains.UserInfo;
import com.company.library_javaEE.enums.Genre;
import com.company.library_javaEE.enums.Language;
import com.company.library_javaEE.exceptions.NotFoundException;
import com.company.library_javaEE.service.book.BookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;


@WebServlet("/genre/*")
public class BookGenreServlet extends HttpServlet {
    BookService bookService = ApplicationContextHolder.getBean( BookService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String[] pathVariables = req.getPathInfo().split("/");
        String genreKey = pathVariables[pathVariables.length - 1];
        Genre searchGenre = Arrays.stream( Genre.values())
                .filter(item -> item.getKey().equals(genreKey))
                .findFirst()
                .orElseThrow(() -> new NotFoundException ("Genre not found"));


        Integer page = Integer.parseInt(Optional.ofNullable(req.getParameter("page")).orElse("1"));
        Integer limit = Integer.parseInt(Optional.ofNullable(req.getParameter("limit")).orElse("12"));
        String search = Optional.ofNullable(req.getParameter("search")).orElse("");

        req.setAttribute("books", bookService.getAllByGenre(searchGenre, page, limit));
        req.setAttribute("pageInfo", UserInfo.builder()
                .hasPrevious(bookService.hasPrevious(page))
                .hasNext(bookService.hasNext(searchGenre, page, limit))
                .number(page)
                .totalPages(bookService.totalPage(searchGenre, limit))
                .build());
        req.setAttribute("search", search);
        req.setAttribute("genres", Genre.values());
        req.setAttribute("languages", Language.values());


        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/BookListByGenre.jsp");
        dispatcher.forward(req, resp);

    }
}
