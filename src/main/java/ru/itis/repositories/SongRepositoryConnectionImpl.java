package ru.itis.repositories;

import lombok.SneakyThrows;
import ru.itis.mappers.RowMapper;
import ru.itis.models.Song;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SongRepositoryConnectionImpl implements SongRepository {
    private Connection connection;

    //language=SQL
    private static final String SQL_INSERT_QUERY = "insert into song(song_title, song_duration, artist_id) " +
            "values(?, ?, ?)";

    private RowMapper<Song> songRowMapper = new RowMapper<Song>() {
        @SneakyThrows
        public Song rowMap(ResultSet resultSet) {
            return Song.builder()
                    .songId(resultSet.getLong("song_id"))
                    .title(resultSet.getString("song_title"))
                    .duration(resultSet.getLong("song_duration"))
                    .artistId(resultSet.getLong("artist_id"))
                    .build();
        }
    };

    public SongRepositoryConnectionImpl(Connection connection) {
        this.connection = connection;
    }

    @SneakyThrows
    public Optional<Song> findOne(Long id) {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * from song WHERE song_id = " + id);
        resultSet.next();
        return Optional.of(songRowMapper.rowMap(resultSet));
    }

    @SneakyThrows
    public void save(Song model) {
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT_QUERY,
                PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, model.getTitle());
        statement.setLong(2, model.getDuration());
        statement.setLong(3, model.getArtistId());
        ResultSet resultSet = statement.getGeneratedKeys();
        while (resultSet.next()) {
            model.setSongId(resultSet.getLong("song_id"));
        }
        statement.executeUpdate();
    }

    public void delete(Long id) {

    }

    //????
    @SneakyThrows
    public Optional<List<Song>> findAll() {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from song");
        List<Song> songs = new ArrayList<>();
        while (resultSet.next()) {
            songs.add(songRowMapper.rowMap(resultSet));
        }
        return Optional.of(songs);
    }
}
