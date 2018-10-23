package ru.itis.maletskov.context;

import lombok.SneakyThrows;
import ru.itis.maletskov.repositories.UsersRepository;
import ru.itis.maletskov.repositories.UsersRepositoryConnectionImpl;
import ru.itis.maletskov.services.UsersService;
import ru.itis.maletskov.services.UsersServiceImpl;
import ru.itis.maletskov.repositories.ArtistRepository;
import ru.itis.maletskov.repositories.ArtistRepositoryConnectionImpl;
import ru.itis.maletskov.services.ArtistService;
import ru.itis.maletskov.services.ArtistServiceImpl;

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
        ArtistRepository artistRepository = new ArtistRepositoryConnectionImpl(connection);
        UsersService usersService = new UsersServiceImpl(usersRepository);
        ArtistService artistService = new ArtistServiceImpl(artistRepository);
        ServletContext context = sce.getServletContext();
        context.setAttribute("usersService", usersService);
        context.setAttribute("artistService", artistService);
    }
}
