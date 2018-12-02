package ru.itis.maletskov.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.itis.maletskov.models.Artist;


public class ArtistRowMapper {
    public static final RowMapper<Artist> artistRowMapper = (resultSet, i) -> Artist.builder()
            .artistId(resultSet.getInt("artist_id"))
            .nickname(resultSet.getString("nickname"))
            .firstName(resultSet.getString("first_name"))
            .lastName(resultSet.getString("last_name"))
            .birthday(resultSet.getDate("birthday"))
            .artistImgSrc(resultSet.getString("img_src"))
            .build();
}
