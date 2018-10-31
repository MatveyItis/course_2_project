package ru.itis.maletskov.repositories;

import lombok.SneakyThrows;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.maletskov.models.Song;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class SongRepositoryJdbcTemplateImpl implements SongRepository {
    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private static final String SQL_INSERT = "insert into song(song_title, song_duration, artist_id) values(?, ?, ?)";

    //language=SQL
    private static final String SQL_SELECT_SONG_BY_ID = "select * from song where song_id = ?";

    //language=SQL
    private static final String SQL_SELECT_SONGS = "select * from song";

    //language=SQL
    private static final String SQL_DELETE_SONG_BY_ID = "delete from song where song_id = ?";


    public SongRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public RowMapper<Song> songRowMapper = (resultSet, i) -> Song.builder()
            .songId(resultSet.getInt("song_id"))
            .title(resultSet.getString("song_title"))
            .duration(resultSet.getInt("song_duration"))
            .artistId(resultSet.getInt("artist_id"))
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
                    PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"client_id"});
                    statement.setString(1, model.getTitle());
                    statement.setInt(2, model.getDuration());
                    statement.setInt(3, model.getArtistId());
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
}