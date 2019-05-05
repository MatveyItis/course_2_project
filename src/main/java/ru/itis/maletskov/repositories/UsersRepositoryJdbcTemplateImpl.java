package ru.itis.maletskov.repositories;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.dao.IncorrectUpdateSemanticsDataAccessException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.maletskov.models.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Repository
public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private final String SQL_SEARCH_BY_LIKE = "select * from client " +
            "where lower(first_name || last_name) like lower(?) " +
            "or lower(last_name || first_name) like lower(?)";

    //language=SQL
    private final String SQL_UPDATE_USER = "update client " +
            "set (first_name, last_name, email, hash_password) = (?, ?, ?, ?) " +
            "where client_id = ?";

    //language=SQL
    private final String SQL_INSERT = "insert into client(first_name, last_name, email, hash_password) " +
            "values (?, ?, ?, ?);";

    //language=SQL
    private final String SQL_DELETE = "delete from client where client_id = ?";

    //language=SQL
    private final String SQL_SELECT_USER_BY_EMAIL = "select * from client " +
            "join library l on client.client_id = l.client_id " +
            "where client.email = ?";

    //language=SQL
    private final String SQL_SELECT_USER_WITH_SONGS = "";

    //language=SQL
    private final String SQL_SELECT_USER = "select * from client " +
            "join library l on client.client_id = l.client_id " +
            "where client.client_id = ?";

    //language=SQL
    private final String SQL_SELECT_USERS = "select * from client " +
            "join library l on client.client_id = l.client_id";

    @Autowired
    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<User> findOneByEmail(String email) {
        try {
            return Optional.empty();
            //return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_EMAIL, ContextRowMapper.userRowMapper, email));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<User>> searchPeopleByName(String name) {
        try {
            //List<User> userList = jdbcTemplate.query(SQL_SEARCH_BY_LIKE, ContextRowMapper.searchingUserRowmapper, name + '%', name + '%');
            return Optional.empty();
        } catch (IncorrectResultSizeDataAccessException | IncorrectResultSetColumnCountException ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findOne(Long id) {
        try {
            return Optional.empty();
            //return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_USER, ContextRowMapper.userRowMapper, id));
        } catch (IncorrectResultSizeDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public User save(User model) {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(
                    connection -> {
                        PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"client_id"});
                        statement.setString(1, model.getFirstName());
                        statement.setString(2, model.getLastName());
                        statement.setString(3, model.getEmail());
                        statement.setString(4, model.getPassword());
                        return statement;
                    }, keyHolder);
            model.setId(keyHolder.getKey().longValue());
            return model;
        } catch (IncorrectUpdateSemanticsDataAccessException e) {
            throw new IncorrectUpdateSemanticsDataAccessException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            jdbcTemplate.update(SQL_DELETE, id);
        } catch (IncorrectUpdateSemanticsDataAccessException e) {
            throw new IncorrectUpdateSemanticsDataAccessException(e.getMessage());
        }
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>();
    }

    @Override
    public void update(User model) {
        try {
            jdbcTemplate.update(SQL_UPDATE_USER,
                    model.getFirstName(),
                    model.getLastName(),
                    model.getEmail(),
                    model.getPassword(),
                    model.getId());
        } catch (IncorrectUpdateSemanticsDataAccessException e) {
            throw new IncorrectUpdateSemanticsDataAccessException(e.getMessage());
        }
    }
}
