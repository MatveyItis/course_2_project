package ru.itis.maletskov.context;

import lombok.SneakyThrows;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.maletskov.repositories.LibraryRepository;
import ru.itis.maletskov.repositories.LibraryRepositoryJdbcTemplateImpl;
import ru.itis.maletskov.repositories.UsersRepository;
import ru.itis.maletskov.repositories.UsersRepositoryJdbcTemplateImpl;
import ru.itis.maletskov.services.LibraryService;
import ru.itis.maletskov.services.LibraryServiceImpl;
import ru.itis.maletskov.services.UsersService;
import ru.itis.maletskov.services.UsersServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ComponentsListener implements ServletContextListener {
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "12ER56ui78";
    private static final String URL = "jdbc:postgresql://localhost:5432/musicservice";

    @SneakyThrows
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        UsersRepository usersRepository = new UsersRepositoryJdbcTemplateImpl(dataSource);
        UsersService usersService = new UsersServiceImpl(usersRepository);
        LibraryRepository libraryRepository = new LibraryRepositoryJdbcTemplateImpl(dataSource);
        LibraryService libraryService = new LibraryServiceImpl(libraryRepository);
        ServletContext context = sce.getServletContext();
        context.setAttribute("usersService", usersService);
        context.setAttribute("libraryService", libraryService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
