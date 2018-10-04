package ru.itis.app;

import lombok.SneakyThrows;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;
import ru.itis.repositories.UsersRepositoryConnectionImpl;

import java.sql.Connection;
import java.sql.DriverManager;

public class UsersRepositoryDemo {
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "12ER56ui78";
    private static final String URL = "jdbc:postgresql://localhost:5432/";

    @SneakyThrows
    public static void main(String[] args) {
        Connection connection =
                DriverManager.getConnection(URL, USERNAME, PASSWORD);
        UsersRepository usersRepository = new UsersRepositoryConnectionImpl(connection);
        User user1 = User.builder()
                .firstName("Anya")
                .lastName("Kuzmenko")
                .email("Kuzya228@mail.ru")
                .hashPassword("nadiufnisfaefqwbefisdbfdsfiwuebfuibsdf")
                .build();
        usersRepository.save(user1);
    }
}
