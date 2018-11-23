package ru.itis.maletskov.repositories;

import lombok.SneakyThrows;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.maletskov.models.Artist;
import ru.itis.maletskov.models.Song;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class SongRepositoryJdbcTemplateImpl implements SongRepository {
    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private static final String SQL_INSERT = "insert into " +
            "song(song_title, artist_id, song_src) values(?, ?, ?)";

    //language=SQL
    private static final String SQL_SELECT_SONG_BY_ID = "select song_id, " +
            "song_title, song_duration, song_src, a1.artist_id as artist_id, " +
            "a1.nickname as nickname, a1.first_name as first_name, " +
            "a1.last_name as last_name, a1.birthday as artist_birthday, " +
            "a1.img_src as artist_img_src from song " +
            "join artist as a1 on song.artist_id = a1.artist_id " +
            "where song_id = ?";

    //language=SQL
    private static final String SQL_SELECT_SONGS = "select song_id, " +
            "song_title, song_duration, song_src, a1.artist_id as artist_id, " +
            "a1.nickname as nickname, a1.first_name as first_name, " +
            "a1.last_name as last_name, a1.birthday as artist_birthday, " +
            "a1.img_src as artist_img_src from song " +
            "join artist as a1 on song.artist_id = a1.artist_id";

    //language=SQL
    private static final String SQL_DELETE_SONG_BY_ID = "delete from song where song_id = ?";


    public SongRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public RowMapper<Song> songRowMapper = (resultSet, i) -> Song.builder()
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

    @SneakyThrows
    @Override
    public Optional<Song> findOne(Integer id) {
        return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_SONG_BY_ID, songRowMapper, id));
    }

    @SneakyThrows
    @Override
    public void save(Song model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"song_id"});
                    statement.setString(1, model.getTitle());
                    statement.setInt(2, model.getArtist().getArtistId());
                    statement.setString(3, model.getSongSrc());
                    return statement;
                }, keyHolder);
        model.setSongId(keyHolder.getKey().intValue());
    }

    @SneakyThrows
    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(SQL_DELETE_SONG_BY_ID, id);
    }

    @SneakyThrows
    @Override
    public Optional<List<Song>> findAll() {
        return Optional.of(jdbcTemplate.query(SQL_SELECT_SONGS, songRowMapper));
    }

    @Override
    public Optional<List<Song>> searchByName(String songName) {
        return Optional.empty();
    }
}