package ru.itis.maletskov.repositories;

import lombok.SneakyThrows;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.maletskov.models.Library;
import ru.itis.maletskov.models.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private static final String SQL_CREATE_LIBRARY_FOR_USER = "insert into library(client_id) values(?)";

    //language=SQL
    private static final String SQL_INSERT = "insert into client(first_name, last_name, email, hash_password) " +
            "values (?, ?, ?, ?);";

    //language=SQL
    private static final String SQL_DELETE = "delete from client where client_id = ?";

    //language=SQL
    private static final String SQL_SELECT_USER_BY_EMAIL = "select * from client " +
            "join library l on client.client_id = l.client_id " +
            "where email = ?";

    //language=SQL
    private static final String SQL_SELECT_USER = "select * from client " +
            "join library l on client.client_id = l.client_id " +
            "where client.client_id = ?";

    //language=SQL
    private static final String SQL_SELECT_USERS = "select * from client " +
            "join library l on client.client_id = l.client_id";

    public RowMapper<User> userRowMapper = (resultSet, i) -> User.builder()
            .clientId(resultSet.getInt("client_id"))
            .firstName(resultSet.getString("first_name"))
            .lastName(resultSet.getString("last_name"))
            .email(resultSet.getString("email"))
            .hashPassword(resultSet.getString("hash_password"))
            .library(Library.builder()
                    .clientId(resultSet.getInt("client_id"))
                    .libraryId(resultSet.getInt("library_id"))
                    .build())
            .build();


    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @SneakyThrows
    @Override
    public Optional<List<User>> findAllByFirstName(String firstName) {
        return Optional.empty();
    }

    @SneakyThrows
    @Override
    public Optional<User> findOneByEmail(String email) {
        return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_EMAIL, userRowMapper, email));
    }

    @SneakyThrows
    @Override
    public Optional<User> findOne(Integer id) {
        return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_USER, userRowMapper, id));
    }

    @SneakyThrows
    @Override
    public void save(User model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"client_id"});
                    statement.setString(1, model.getFirstName());
                    statement.setString(2, model.getLastName());
                    statement.setString(3, model.getEmail());
                    statement.setString(4, model.getHashPassword());
                    return statement;
                }, keyHolder);
        model.setClientId(keyHolder.getKey().intValue());
        createLibraryForUser(model);
    }

    @SneakyThrows
    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }

    @SneakyThrows
    @Override
    public Optional<List<User>> findAll() {
        return Optional.of(jdbcTemplate.query(SQL_SELECT_USERS, userRowMapper));
    }

    /*Service methods*/
    @SneakyThrows
    private void createLibraryForUser(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_CREATE_LIBRARY_FOR_USER, new String[]{"library_id"});
            statement.setLong(1, user.getClientId());
            return statement;
        }, keyHolder);
        Library library = Library.builder()
                .libraryId(keyHolder.getKey().intValue())
                .clientId(user.getClientId())
                .build();
        user.setLibrary(library);
    }
}