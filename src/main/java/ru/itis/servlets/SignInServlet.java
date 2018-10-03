package ru.itis.servlets;

import lombok.SneakyThrows;
import ru.itis.forms.LoginForm;
import ru.itis.repositories.UsersRepository;
import ru.itis.repositories.UsersRepositoryConnectionImpl;
import ru.itis.services.UsersService;
import ru.itis.services.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

public class SignInServlet extends HttpServlet {
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "12ER56ui78";
    private static final String URL = "jdbc:postgresql://localhost:5432/";

    private UsersService usersService;

    @Override
    @SneakyThrows
    public void init() throws ServletException {
        Class.forName("org.postgresql.Driver");
        Connection connection =
                DriverManager.getConnection(URL, USERNAME, PASSWORD);
        UsersRepository usersRepository = new UsersRepositoryConnectionImpl(connection);
        usersService = new UsersServiceImpl(usersRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-Type", "text/html");
        PrintWriter writer = response.getWriter();
        writer.print("<form method='post'>\n" +
                "\t\t<label for='email'>E-mail</label><br>\n" +
                "\t\t<input type='text' id='email' name='email' placeholder='E-mail'><br>\n" +
                "\t\t<label for='password'>Password</label><br>\n" +
                "\t\t<input type='password' id='password' name='password' placeholder='Password'><br> \n" +
                "\t\t<input type='submit' value='Sign In'>\n" +
                "</form>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        LoginForm loginForm = LoginForm.builder()
                .email(email)
                .password(password)
                .build();

        usersService.signIn(loginForm);
        response.setHeader("Content-Type", "text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<h1>You are in the System!!</h1>");
    }
}
