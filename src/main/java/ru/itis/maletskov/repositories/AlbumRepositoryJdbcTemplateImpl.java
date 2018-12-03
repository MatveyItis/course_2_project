package ru.itis.maletskov.repositories;

import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.itis.maletskov.models.Album;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class AlbumRepositoryJdbcTemplateImpl implements AlbumRepository {
    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private final String SQL_SELECT_ALBUM_WITH_SONGS = "select album.album_id, " +
            "album.artist_id, a2.nickname as artist_nickname, a2.first_name as artist_first_name, " +
            "a2.last_name as artist_last_name, a2.birthday as artist_birthday, a2.img_src as artist_img_src, " +
            "album.album_title, album.album_year, album.album_cover_src, a.song_id as song_id, " +
            "s2.song_title as song_title, s2.song_duration as song_duration, s2.song_src as song_src from album " +
            "join albums_songs a on album.album_id = a.album_id " +
            "join song s2 on a.song_id = s2.song_id " +
            "join artist a2 on album.artist_id = a2.artist_id " +
            "where album.album_id = ?";

    public AlbumRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Album> findOne(Integer id) {
        return Optional.empty();
    }

    @Override
    public void save(Album model) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Optional<List<Album>> findAll() {
        return Optional.empty();
    }
}
