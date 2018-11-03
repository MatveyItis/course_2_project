package ru.itis.maletskov.servlets;

import lombok.SneakyThrows;
import ru.itis.maletskov.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpServlet extends HttpServlet {
    private UsersService usersService;

    @Override
    @SneakyThrows
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        usersService = (UsersService) context.getAttribute("usersService");
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        request.getServletContext().getRequestDispatcher("/views/signUp.jsp").forward(request, response);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}
