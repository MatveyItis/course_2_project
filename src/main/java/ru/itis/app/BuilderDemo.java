package ru.itis.app;

import lombok.SneakyThrows;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepositoryConnectionImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Optional;

public class BuilderDemo {
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "12ER56ui78";
    private static final String URL = "jdbc:postgresql://localhost:5432/";

    @SneakyThrows
    public static void main(String[] args) {
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        UsersRepositoryConnectionImpl usersRepository = new UsersRepositoryConnectionImpl(connection);
        Optional<User> userOptional = usersRepository.findOneByEmail("maletskov56@mail.ru");
        User user = userOptional.get();
        System.out.println(user.getHashPassword());
        //AlbumRepositoryConnectionImpl albumRepository = new AlbumRepositoryConnectionImpl(connection);
        //System.out.println(albumRepository.findOne(1L));
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
