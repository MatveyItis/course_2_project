package ru.itis.maletskov.servlets;

import lombok.SneakyThrows;
import ru.itis.maletskov.services.LibraryService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddSongServlet extends HttpServlet {
    private LibraryService libraryService;

    @SneakyThrows
    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        libraryService = (LibraryService) context.getAttribute("libraryService");
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

    }
}
