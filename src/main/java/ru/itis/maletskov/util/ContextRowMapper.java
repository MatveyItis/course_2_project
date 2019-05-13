package ru.itis.maletskov.util;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.itis.maletskov.models.Img;

@Component
public class ContextRowMapper {
    public static RowMapper<Img> imgRowMapper = (resultSet, i) -> {
        Img img = new Img();
        img.setId(resultSet.getLong("id"));
        img.setName(resultSet.getString("name"));
        img.setFileName(resultSet.getString("filename"));
        return img;
    };
}
