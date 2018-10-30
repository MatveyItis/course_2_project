package ru.itis.maletskov.repositories;

import lombok.SneakyThrows;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.maletskov.models.Song;
import ru.itis.maletskov.models.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private static final String SQL_CREATE_LIBRARY_FOR_USER = "insert into library(client_id) values(?)";

    //language=SQL
    private static final String SQL_INSERT = "insert into client(first_name, last_name, email, hash_password) " +
            "values (?, ?, ?, ?);";

    //language=SQL
    private static final String SQL_INSERT_TO_SONGS_LIBRARY = "insert into songs_library(library_id, song_id) values(?, ?)";

    //language=SQL
    private static final String SQL_DELETE = "delete from client where client_id = ?";

    //language=SQL
    private static final String SQL_SELECT_USER_BY_EMAIL = "select * from client " +
            "join library l on client.client_id = l.client_id " +
            "left join songs_library l2 on l.library_id = l2.library_id " +
            "left join song s2 on l2.song_id = s2.song_id " +
            "where client.email = ?";

    //language=SQL
    private static final String SQL_SELECT_USER = "select * from client " +
            "join library l on client.client_id = l.client_id " +
            "left join songs_library l2 on l.library_id = l2.library_id " +
            "left join song s2 on l2.song_id = s2.song_id " +
            "where client.client_id = ?";

    //language=SQL
    private static final String SQL_SELECT_USERS = "select * from client " +
            "join library l on client.client_id = l.client_id " +
            "left join songs_library l2 on l.library_id = l2.library_id " +
            "left join song s2 on l2.song_id = s2.song_id";


    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public RowMapper<User> userWithSongsRowMapper = (resultSet, i) -> {
        List<Song> songs = new ArrayList<>();
        User user = null;
        boolean isHaving = false;
        do {
            if (!isHaving) {
                isHaving = true;
                user = User.builder()
                        .clientId(resultSet.getInt("client_id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .email(resultSet.getString("email"))
                        .hashPassword(resultSet.getString("hash_password"))
                        .libraryId(resultSet.getInt("library_id"))
                        .build();
            }
            Song song = Song.builder()
                    .songId(resultSet.getInt("song_id"))
                    .title(resultSet.getString("song_title"))
                    .duration(resultSet.getInt("song_duration"))
                    .artistId(resultSet.getInt("artist_id"))
                    .build();
            if (song.getSongId() != 0) {
                songs.add(song);
            }
        } while (resultSet.next());
        if (user != null) {
            user.setSongs(songs);
        }
        return user;
    };

    @SneakyThrows
    @Override
    public Optional<List<User>> findAllByFirstName(String firstName) {
        return Optional.empty();
    }

    @SneakyThrows
    @Override
    public Optional<User> findOneByEmail(String email) {
        return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_EMAIL, userWithSongsRowMapper, email));
    }

    @SneakyThrows
    @Override
    public Optional<User> findOne(Long id) {
        return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_USER, userWithSongsRowMapper, id));
    }

    @SneakyThrows
    @Override
    public void save(User model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"client_id"});
                    statement.setString(1, model.getFirstName());
                    statement.setString(2, model.getLastName());
                    statement.setString(3, model.getEmail());
                    statement.setString(4, model.getHashPassword());
                    return statement;
                }, keyHolder);
        model.setClientId(keyHolder.getKey().intValue());
        createLibraryForUser(model);
    }

    @SneakyThrows
    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }

    @SneakyThrows
    @Override
    public Optional<List<User>> findAll() {
        return Optional.of(jdbcTemplate.query(SQL_SELECT_USERS, userWithSongsRowMapper));
    }

    @SneakyThrows
    @Override
    public void saveSongToLibrary(Song song, Integer libraryId) {
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_TO_SONGS_LIBRARY);
            statement.setInt(1, libraryId);
            statement.setInt(2, song.getSongId());
            return statement;
        });
    }


    /*Service methods*/
    @SneakyThrows
    private void createLibraryForUser(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_CREATE_LIBRARY_FOR_USER, new String[]{"library_id"});
            statement.setLong(1, user.getClientId());
            return statement;
        }, keyHolder);
        user.setLibraryId(keyHolder.getKey().intValue());
    }
}