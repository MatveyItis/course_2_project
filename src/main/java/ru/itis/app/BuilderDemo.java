package ru.itis.app;

import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BuilderDemo {
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "12ER56ui78";
    private static final String URL = "jdbc:postgresql://localhost:5432/";

    @SneakyThrows
    public static void main(String[] args) {
        String birthday = "10-10-1998";
        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(birthday);
        java.sql.Date datesql = new java.sql.Date(date.getTime());
        System.out.println(datesql);
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
