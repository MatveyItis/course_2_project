package ru.itis.maletskov.servlets;

import lombok.SneakyThrows;
import ru.itis.maletskov.forms.LoginForm;
import ru.itis.maletskov.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignInServlet extends HttpServlet {
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
        request.getServletContext().getRequestDispatcher("/views/signIn.jsp").forward(request, response);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        LoginForm loginForm = LoginForm.builder()
                .email(email)
                .password(password)
                .build();
        if (usersService.signIn(loginForm)) {
            response.sendRedirect("/successLogIn");
        } else {
            response.sendRedirect("/signUpFailed");
        }
    }
}
