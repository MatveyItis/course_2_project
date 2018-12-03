package ru.itis.maletskov.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.itis.maletskov.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContextRowMapper {
    public static Map<Library, List<Song>> libraryWithSongsMap = new HashMap<>();
    public static Library theOnlyLibrary;

    public static final RowMapper<Artist> artistRowMapper = (resultSet, i) -> Artist.builder()
            .artistId(resultSet.getInt("artist_id"))
            .nickname(resultSet.getString("nickname"))
            .firstName(resultSet.getString("first_name"))
            .lastName(resultSet.getString("last_name"))
            .birthday(resultSet.getDate("birthday"))
            .artistImgSrc(resultSet.getString("img_src"))
            .build();

    public static final RowMapper<Auth> authRowMapper = (resultSet, i) -> Auth.builder()
            .cookieValue(resultSet.getString("cookie_value"))
            .clientId(resultSet.getInt("client_id"))
            .build();

    public static final RowMapper<Library> libraryWithoutSongsRowMapper = (resultSet, i) -> Library.builder()
            .clientId(resultSet.getInt("client_id"))
            .libraryId(resultSet.getInt("library_id"))
            .build();

    public static final RowMapper<Library> libraryRowMapper = (resultSet, i) -> {
        if (libraryWithSongsMap.size() == 0) {
            Library newLibrary = libraryWithoutSongsRowMapper.mapRow(resultSet, i);
            libraryWithSongsMap.put(newLibrary, new ArrayList<>());
            theOnlyLibrary = newLibrary;
        }
        Song song = Song.builder()
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
        if (song.getSongId() != 0) {
            libraryWithSongsMap.get(theOnlyLibrary).add(song);
        }
        return theOnlyLibrary;
    };

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
