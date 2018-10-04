package ru.itis.servlets;

import lombok.SneakyThrows;
import ru.itis.forms.UserForm;
import ru.itis.repositories.UsersRepository;
import ru.itis.repositories.UsersRepositoryConnectionImpl;
import ru.itis.services.UsersService;
import ru.itis.services.UsersServiceImpl;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

public class SignUpServlet extends HttpServlet {
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
        response.setHeader("Content-Type", "text/html");
        PrintWriter writer = response.getWriter();
        writer.print("<div class='container' style='width:300px;height:400px'>" +
                "    \t\t<form method='post'>\n" +
                "    \t\t<label for='firstName'>First Name</label><br>\n" +
                "    \t\t<input type='text' name='firstName' placeholder='First Name' id='firstName'><br>\n" +
                "    \t\t<label for='lastName'>LastName</label><br>\n" +
                "    \t\t<input type='text' name='lastName' placeholder='LastName' id='lastName'><br>\n" +
                "    \t\t<label for='email'>Email</label><br>\n" +
                "    \t\t<input type='text' name='email' placeholder='Email' id='email'><br>\n" +
                "    \t\t<label for='password'>Password</label><br>\n" +
                "    \t\t<input type='password' name='password' placeholder='Password' id='password'><br>\n" +
                "    \t\t<input type='submit' value='Sign Up'>\n" +
                "    \t\t</form>" +
                "</div>");
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        UserForm userForm = UserForm.builder()
                .email(email)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .build();
        usersService.signUp(userForm);
        response.sendRedirect("/signIn");
    }
}
