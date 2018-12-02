package ru.itis.maletskov.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.itis.maletskov.models.Auth;

public class AuthRowMapper {
    public static final RowMapper<Auth> authRowMapper = (resultSet, i) -> Auth.builder()
            .cookieValue(resultSet.getString("cookie_value"))
            .clientId(resultSet.getInt("client_id"))
            .build();
}
