package ru.itis.context;

import lombok.SneakyThrows;
import ru.itis.repositories.UsersRepository;
import ru.itis.repositories.UsersRepositoryConnectionImpl;
import ru.itis.services.UsersService;
import ru.itis.services.UsersServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.DriverManager;

public class UserServiceListener implements ServletContextListener {
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "12ER56ui78";
    private static final String URL = "jdbc:postgresql://localhost:5432/";

    @SneakyThrows
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        UsersRepository usersRepository = new UsersRepositoryConnectionImpl(connection);
        UsersService usersService = new UsersServiceImpl(usersRepository);
        ServletContext context = sce.getServletContext();
        context.setAttribute("usersService", usersService);
    }
}
