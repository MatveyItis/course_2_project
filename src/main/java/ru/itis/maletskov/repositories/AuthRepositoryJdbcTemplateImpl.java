package ru.itis.maletskov.repositories;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.maletskov.models.Auth;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class AuthRepositoryJdbcTemplateImpl implements AuthRepository {
    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private final static String SQL_INSERT = "insert into auth (cookie_value, client_id) values (?, ?);";

    //language=SQL
    private final static String SQL_SELECT_BY_CLIENT_ID = "select * from auth where auth_id = ?";

    //language=SQL
    private final static String SQL_SELECT_AUTH_BY_COOKIE_VALUE = "select * from auth where cookie_value = ?;";

    //language=SQL
    private final static String SQL_DELETE_BY_CLIENT_ID = "delete from auth where client_id = ?";

    public RowMapper<Auth> authRowMapper = (resultSet, i) -> Auth.builder()
            .cookieValue(resultSet.getString("cookie_value"))
            .clientId(resultSet.getInt("client_id"))
            .build();

    public AuthRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Auth> findOne(Integer id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_CLIENT_ID, authRowMapper, id));
        } catch (IncorrectResultSizeDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Auth> findAuthByCookieValue(String cookieValue) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_AUTH_BY_COOKIE_VALUE, authRowMapper, cookieValue));
        } catch (IncorrectResultSizeDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(Auth model) {
        Optional<Auth> prevAuthOptional = findOne(model.getClientId());
        if (prevAuthOptional.isPresent()) {
            delete(model.getClientId());
        }
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"auth_id"});
            statement.setString(1, model.getCookieValue());
            statement.setInt(2, model.getClientId());
            return statement;
        });
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(SQL_DELETE_BY_CLIENT_ID, id);
    }

    @Override
    public Optional<List<Auth>> findAll() {
        return Optional.empty();
    }
}
