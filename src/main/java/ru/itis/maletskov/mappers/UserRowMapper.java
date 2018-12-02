package ru.itis.maletskov.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.itis.maletskov.models.Library;
import ru.itis.maletskov.models.User;

public class UserRowMapper {
    public static final RowMapper<User> userRowMapper = (resultSet, i) ->
            User.builder()
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
}
