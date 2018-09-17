package ru.itis.repositories;

import lombok.SneakyThrows;
import ru.itis.mappers.RowMapper;
import ru.itis.models.Song;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class SongRepositoryConnectionImpl implements SongRepository {
    private Connection connection;

    //language=SQL
    private static final String SQL_ = "";

    private RowMapper<Song> songRowMapper = new RowMapper<Song>() {
        @SneakyThrows
        public Song rowMap(ResultSet resultSet) {
            return Song.builder()
                    .songId(resultSet.getInt("song_id"))
                    .title(resultSet.getString("song_title"))
                    .duration(resultSet.getInt("song_duration"))
                    .artistId(resultSet.getInt("artist_id"))
                    .build();
        }
    };

    public SongRepositoryConnectionImpl(Connection connection) {
        this.connection = connection;
    }

    @SneakyThrows
    public Optional<Song> findOne(int id) {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * from songs WHERE song_id = " + id);
        resultSet.next();
        return Optional.of(songRowMapper.rowMap(resultSet));
    }

    public void save(Song model) {

    }

    public void delete(int id) {

    }

    public Optional<List<Song>> findAll() {
        return null;
    }
}
