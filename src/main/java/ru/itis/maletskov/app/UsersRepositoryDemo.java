package ru.itis.maletskov.app;

import lombok.SneakyThrows;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.maletskov.models.User;
import ru.itis.maletskov.repositories.UsersRepository;
import ru.itis.maletskov.repositories.UsersRepositoryJdbcTemplateImpl;

public class UsersRepositoryDemo {
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "12ER56ui78";
    private static final String URL = "jdbc:postgresql://localhost:5432/";

    @SneakyThrows
    public static void main(String[] args) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        UsersRepository usersRepository = new UsersRepositoryJdbcTemplateImpl(dataSource);
        User user1 = User.builder()
                .firstName("Anya")
                .lastName("Kuzmenko")
                .email("Kuzya228@mail.ru")
                .hashPassword("nadiufnisfaefqwbefisdbfdsfiwuebfuibsdf")
                .build();
        usersRepository.save(user1);
    }
}
