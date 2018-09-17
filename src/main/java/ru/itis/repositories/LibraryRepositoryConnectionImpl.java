package ru.itis.repositories;

import lombok.SneakyThrows;
import ru.itis.mappers.RowMapper;
import ru.itis.models.Library;
import ru.itis.models.Song;
import ru.itis.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class LibraryRepositoryConnectionImpl implements LibraryRepository {
    private Connection connection;

    //language=SQL
    private static final String SQL_DELETE_LIBRARY_WITH_SONGS = "DELETE FROM library" +
            " WHERE client_id = ?";

    //language=SQL
    private static final String SQL_SELECT_LIBRARY_WITH_SONGS = "select * " +
            "from library " +
            "join songs s on library.song_id = s.song_id " +
            "join clients c2 on library.client_id = c2.client_id " +
            "where library.client_id = ?";

    public LibraryRepositoryConnectionImpl(Connection connection) {
        this.connection = connection;
    }

    private RowMapper<Library> libraryRowMapper = new RowMapper<Library>() {
        @SneakyThrows
        public Library rowMap(ResultSet resultSet) {
            return Library.builder()
                    .clientId(resultSet.getInt("client_id"))
                    .build();
        }
    };

    @SneakyThrows
    public Optional<Library> findOne(int client_id) {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_LIBRARY_WITH_SONGS);
        preparedStatement.setInt(1, client_id);
        if (preparedStatement.execute()) {
            ResultSet resultSet = preparedStatement.getResultSet();
            List<Song> songs = new ArrayList<>();
            Library library = null;
            User client = null;
            while (resultSet.next()) {
                if (library == null) {
                    library = Library.builder()
                            .clientId(resultSet.getInt("client_id"))
                            .build();
                    /*client = User.builder()
                            .clientId(resultSet.getInt("client_id"))
                            .firstName(resultSet.getString("first_name"))
                            .lastName(resultSet.getString("last_name"))
                            .email(resultSet.getString("email"))
                            .hashPassword(resultSet.getString("hash_password"))
                            .build();*/
                }
                Song song = Song.builder()
                        .songId(resultSet.getInt("song_id"))
                        .title(resultSet.getString("song_title"))
                        .duration(resultSet.getInt("song_duration"))
                        .artistId(resultSet.getInt("artist_id"))
                        .build();
                songs.add(song);
            }
            if (library != null) {
                library.setSongs(songs);
                //client.setLibrary(library);
                return Optional.of(library);
            }
        }
        return Optional.empty();
    }

    @SneakyThrows
    public void save(Library model) {

    }

    @SneakyThrows
    public void delete(int id) {
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE_LIBRARY_WITH_SONGS);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    @SneakyThrows
    public Optional<List<Library>> findAll() {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM library " +
                "join songs s on library.song_id = s.song_id");
        List<Library> libraries = new ArrayList<>();
        Set<Integer> ides = new HashSet<>();
        List<Song> songs;
        int a;
        while (resultSet.next()) {
            a = resultSet.getInt("client_id");
            if (!ides.contains(a)) {
                ides.add(a);
                Library library = findOne(a).get();
                libraries.add(library);
            }
        }
        return Optional.of(libraries);
    }
}
