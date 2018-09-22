package ru.itis.app;

import lombok.SneakyThrows;
import ru.itis.models.Library;
import ru.itis.repositories.LibraryRepositoryConnectionImpl;
import ru.itis.repositories.SongRepositoryConnectionImpl;
import ru.itis.repositories.UsersRepositoryConnectionImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BuilderDemo {
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "12ER56ui78";
    private static final String URL = "jdbc:postgresql://localhost:5432/";

    @SneakyThrows
    public static void main(String[] args) {
        Connection connection =
                DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Statement statement = connection.createStatement();
        //LibraryRepositoryConnectionImpl libraryRepository = new LibraryRepositoryConnectionImpl(connection);
        //System.out.println(libraryRepository.findAll());
        UsersRepositoryConnectionImpl usersRepository = new UsersRepositoryConnectionImpl(connection);
        System.out.println(usersRepository.findAllByFirstName("Anastasia"));
        //SongRepositoryConnectionImpl songRepository = new SongRepositoryConnectionImpl(connection);
        //System.out.println(songRepository.findOne(16));
    }
}
