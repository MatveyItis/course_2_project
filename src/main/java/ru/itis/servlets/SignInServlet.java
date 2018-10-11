package ru.itis.servlets;

import lombok.SneakyThrows;
import ru.itis.forms.LoginForm;
import ru.itis.repositories.UsersRepository;
import ru.itis.repositories.UsersRepositoryConnectionImpl;
import ru.itis.services.UsersService;
import ru.itis.services.UsersServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;

public class SignInServlet extends HttpServlet {
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "12ER56ui78";
    private static final String URL = "jdbc:postgresql://localhost:5432/";
    private UsersService usersService;

    @Override
    @SneakyThrows
    public void init() {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        UsersRepository usersRepository = new UsersRepositoryConnectionImpl(connection);
        usersService = new UsersServiceImpl(usersRepository);
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        getServletContext().getRequestDispatcher("/signIn.jsp").forward(request, response);
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
