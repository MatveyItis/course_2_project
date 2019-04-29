package ru.itis.maletskov.repositories;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itis.maletskov.jpamodels.Song;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Repository
public class SongRepositoryJdbcTemplateImpl implements SongRepository {
    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private final String SQL_INSERT = "insert into " +
            "song(song_title, artist_id, song_src) values(?, ?, ?)";

    //language=SQL
    private final String SQL_SELECT_SONG_BY_ID = "select song_id, " +
            "song_title, song_duration, song_src, a1.artist_id as artist_id, " +
            "a1.nickname as nickname, a1.first_name as first_name, " +
            "a1.last_name as last_name, a1.birthday as artist_birthday, " +
            "a1.img_src as artist_img_src from song " +
            "join artist as a1 on song.artist_id = a1.artist_id " +
            "where song_id = ?";

    //language=SQL
    private final String SQL_SELECT_SONGS = "select song_id, " +
            "song_title, song_duration, song_src, a1.artist_id as artist_id, " +
            "a1.nickname as nickname, a1.first_name as first_name, " +
            "a1.last_name as last_name, a1.birthday as artist_birthday, " +
            "a1.img_src as artist_img_src from song " +
            "join artist as a1 on song.artist_id = a1.artist_id";

    //language=SQL
    private final String SQL_DELETE_SONG_BY_ID = "delete from song where song_id = ?";

    @Autowired
    public SongRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @SneakyThrows
    @Override
    public Optional<Song> findOne(Long id) {
        return Optional.empty();
        //return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_SONG_BY_ID, ContextRowMapper.songRowMapper, id));
    }

    @SneakyThrows
    @Override
    public void save(Song model) {
        /*KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"song_id"});
                    statement.setString(1, model.getTitle());
                    statement.setInt(2, model.getArtist().getId());
                    statement.setString(3, model.getSongSrc());
                    return statement;
                }, keyHolder);
        model.setId(keyHolder.getKey().intValue());*/
    }

    @SneakyThrows
    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_SONG_BY_ID, id);
    }

    @SneakyThrows
    @Override
    public List<Song> findAll() {
        return new ArrayList<>();
        //return Optional.of(jdbcTemplate.query(SQL_SELECT_SONGS, ContextRowMapper.songRowMapper));
    }

    @Override
    public void update(Song model) {

    }

    @Override
    public Optional<List<Song>> searchByName(String songName) {
        return Optional.empty();
    }
}