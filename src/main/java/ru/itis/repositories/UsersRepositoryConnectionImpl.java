package ru.itis.repositories;

import lombok.SneakyThrows;
import ru.itis.mappers.RowMapper;
import ru.itis.models.Library;
import ru.itis.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryConnectionImpl implements UsersRepository {
    private Connection connection;

    //language=SQL
    private static final String SQL_INSERT_QUERY = "insert into clients(first_name, last_name, email, hash_password)" +
            "values (?, ?, ?, ?);";

    //language=SQL
    private static final String SQL_DELETE_QUERY = "delete from clients where client_id = ?";

    public UsersRepositoryConnectionImpl(Connection connection) {
        this.connection = connection;
    }

    private RowMapper<User> userRowMapper = new RowMapper<User>() {
        @SneakyThrows
        public User rowMap(ResultSet resultSet) {
            return User.builder()
                    .clientId(resultSet.getInt("client_id"))
                    .email(resultSet.getString("email"))
                    .hashPassword(resultSet.getString("hash_password"))
                    .firstName(resultSet.getString("first_name"))
                    .lastName(resultSet.getString("last_name"))
                    .build();
        }
    };

    public Optional<List<User>> findAllByFirstName(String firstName) {

        return null;
    }

    public Optional<User> findOne(int id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet =
                    statement.executeQuery("SELECT * FROM clients WHERE client_id = " + id);
            resultSet.next();
            return Optional.of(userRowMapper.rowMap(resultSet));
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @SneakyThrows
    public void save(User model) {
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, model.getFirstName());
        statement.setString(2, model.getLastName());
        statement.setString(3, model.getEmail());
        statement.setString(4, model.getHashPassword());
        ResultSet resultSet = statement.getGeneratedKeys();
        while (resultSet.next()) {
            model.setClientId(resultSet.getInt("client_id"));
        }
        statement.executeUpdate();
    }

    @SneakyThrows
    public void delete(int id) {
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE_QUERY);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    @SneakyThrows
    public Optional<List<User>> findAll() {
        Statement statement = connection.createStatement();
        ResultSet resultSet =
                statement.executeQuery("SELECT * FROM clients");
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            User newUser = userRowMapper.rowMap(resultSet);
            users.add(newUser);
        }
        return Optional.of(users);

    }
}
