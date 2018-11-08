package ru.itis.maletskov.repositories;

import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class LibraryRepositoryJdbcTemplateImplTest {
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "12ER56ui78";
    private static final String URL = "jdbc:postgresql://localhost:5432/musicservice";
    private LibraryRepositoryJdbcTemplateImpl libraryRepository;

    @SneakyThrows
    @Before
    public void setUp() {
        DriverManagerDataSource dataSource =
                new DriverManagerDataSource();
        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        libraryRepository = new LibraryRepositoryJdbcTemplateImpl(dataSource);
    }

    @Test
    public void saveSongToLibrary() {

    }

    @Test
    public void removeSongFromLibrary() {

    }

}