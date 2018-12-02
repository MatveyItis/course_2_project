package ru.itis.maletskov.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.itis.maletskov.models.Artist;
import ru.itis.maletskov.models.Song;

public class SongRowMapper {
    public static final RowMapper<Song> songRowMapper = (resultSet, i) -> Song.builder()
            .songId(resultSet.getInt("song_id"))
            .title(resultSet.getString("song_title"))
            .duration(resultSet.getInt("song_duration"))
            .artist(Artist.builder()
                    .artistId(resultSet.getInt("artist_id"))
                    .nickname(resultSet.getString("nickname"))
                    .firstName(resultSet.getString("first_name"))
                    .lastName(resultSet.getString("last_name"))
                    .birthday(resultSet.getDate("artist_birthday"))
                    .artistImgSrc(resultSet.getString("artist_img_src"))
                    .build())
            .songSrc(resultSet.getString("song_src"))
            .build();
}
