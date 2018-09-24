package ru.itis.repositories;

import lombok.SneakyThrows;
import ru.itis.mappers.RowMapper;
import ru.itis.models.Artist;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArtistRepositoryConnectionImpl implements ArtistRepository {
    private Connection connection;

    //language=SQL
    private final static String SQL_DELETE_QUERY = "delete from artist where artist_id = ?";
    //language=SQL
    private final static String SQL_INSERT_QUERY = "insert into artist(last_name, first_name, genre_id) " +
            "values (?, ?, ?)";

    public ArtistRepositoryConnectionImpl(Connection connection) {
        this.connection = connection;
    }

    private RowMapper<Artist> artistRowMapper = new RowMapper<Artist>() {
        @SneakyThrows
        public Artist rowMap(ResultSet resultSet) {
            return Artist.builder()
                    .artistId(resultSet.getLong("artist_id"))
                    .lastName(resultSet.getString("last_name"))
                    .firstName(resultSet.getString("first_name"))
                    .birthday((LocalDate) resultSet.getObject("birthday"))
                    .genreId(resultSet.getLong("genre_id"))
                    .build();
        }
    };

    @SneakyThrows
    public Optional<Artist> findOne(int artistId) {
        Statement statement = connection.createStatement();
        ResultSet resultSet =
                statement.executeQuery("select * from artist where artist_id = " + artistId);
        resultSet.next();
        return Optional.of(artistRowMapper.rowMap(resultSet));
    }

    @SneakyThrows
    public void save(Artist model) {
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, model.getLastName());
        statement.setString(2, model.getFirstName());
        statement.setLong(3, model.getGenreId());
        ResultSet resultSet = statement.getGeneratedKeys();
        while (resultSet.next()) {
            model.setArtistId(resultSet.getLong("artist_id"));
        }
        statement.executeUpdate();
    }

    @SneakyThrows
    public void delete(int artistId) {
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE_QUERY);
        statement.setInt(1, artistId);
        statement.executeUpdate();
    }

    @SneakyThrows
    public Optional<List<Artist>> findAll() {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from artist");
        List<Artist> people = new ArrayList<>();
        while (resultSet.next()) {
            Artist artist = artistRowMapper.rowMap(resultSet);
            people.add(artist);
        }
        return Optional.of(people);
    }
}
