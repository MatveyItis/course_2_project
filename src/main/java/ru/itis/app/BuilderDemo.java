package ru.itis.app;

import lombok.SneakyThrows;
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
        //UsersRepositoryConnectionImpl usersRepository = new UsersRepositoryConnectionImpl(connection);
        //System.out.println(usersRepository.findAllByFirstName("Anastasia"));
        /*SongRepositoryConnectionImpl songRepository = new SongRepositoryConnectionImpl(connection);
        List<Song> list = songRepository.findAll().get();
        for (Song song: list) {
            System.out.println(song);
        }*/
    }
}
