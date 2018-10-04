package ru.itis.repositories;

import lombok.SneakyThrows;
import ru.itis.mappers.RowMapper;
import ru.itis.models.Song;
import ru.itis.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class UsersRepositoryConnectionImpl implements UsersRepository {
    private Connection connection;

    //language=SQL
    private static final String SQL_INSERT_QUERY = "insert into client(first_name, last_name, email, hash_password)" +
            "values (?, ?, ?, ?);";

    //language=SQL
    private static final String SQL_DELETE_QUERY = "delete from client where client_id = ?";

    //language=SQL
    private static final String SQL_FIND_BY_NAME = "select * from client where first_name = ?";

    //language=SQL
    private static final String SQL_SELECT_USER_WITH_SONGS_BY_ID = "select * from client " +
            "join library on client.client_id = library.client_id " +
            "join song s on library.song_id = s.song_id " +
            "where client.client_id = ?";

    //language=SQL
    private static final String SQL_SELECT_USERS_WITH_SONGS = "select * from client " +
            "join library on client.client_id = library.client_id " +
            "join song s on library.song_id = s.song_id ";

    //language=SQL
    private static final String SQL_SELECT_USER_BY_EMAIL = "select * from client where email = ?";

    private Map<User, List<Song>> userWithSongsMap;
    private Map<Long, User> userIdWithSongsMap;
    private User theOnlyUser;

    public UsersRepositoryConnectionImpl(Connection connection) {
        this.connection = connection;
    }

    private RowMapper<User> userWithoutSongsRowMapper = new RowMapper<>() {
        @SneakyThrows
        public User rowMap(ResultSet resultSet) {
            return User.builder()
                    .clientId(resultSet.getLong("client_id"))
                    .email(resultSet.getString("email"))
                    .hashPassword(resultSet.getString("hash_password"))
                    .firstName(resultSet.getString("first_name"))
                    .lastName(resultSet.getString("last_name"))
                    .build();
        }
    };

    private RowMapper<User> userWithSongsForOneUserRowMapper = new RowMapper<>() {
        @Override
        @SneakyThrows
        public User rowMap(ResultSet resultSet) {
            if (userWithSongsMap.size() == 0) {
                User newUser = userWithoutSongsRowMapper.rowMap(resultSet);
                userWithSongsMap.put(newUser, new ArrayList<>());
                theOnlyUser =  newUser;
            }
            Song song = Song.builder()
                    .songId(resultSet.getLong("song_id"))
                    .title(resultSet.getString("song_title"))
                    .duration(resultSet.getLong("song_duration"))
                    .artistId(resultSet.getLong("artist_id"))
                    .build();
            userWithSongsMap.get(theOnlyUser).add(song);
            return theOnlyUser;
        }
    };

    private RowMapper<User> userWithSongsRowMapper = new RowMapper<>() {
        @SneakyThrows
        @Override
        public User rowMap(ResultSet resultSet) {
            Long currentUserId = resultSet.getLong("client_id");
            if (!userIdWithSongsMap.containsKey(currentUserId)) {
                User newUser = userWithoutSongsRowMapper.rowMap(resultSet);
                newUser.setSongs(new ArrayList<>());
                userIdWithSongsMap.put(currentUserId, newUser);
            }
            Song song = Song.builder()
                    .songId(resultSet.getLong("song_id"))
                    .title(resultSet.getString("song_title"))
                    .artistId(resultSet.getLong("artist_id"))
                    .duration(resultSet.getLong("song_duration"))
                    .build();
            User currentUser = userIdWithSongsMap.get(currentUserId);
            List<Song> songsOfUser = currentUser.getSongs();
            songsOfUser.add(song);
            return currentUser;
        }
    };

    @SneakyThrows
    public Optional<List<User>> findAllByFirstName(String firstName) {
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_NAME);
        statement.setString(1, firstName);
        ResultSet resultSet = statement.executeQuery();
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            User newUser = userWithoutSongsRowMapper.rowMap(resultSet);
            users.add(newUser);
        }
        return Optional.of(users);
    }

    @Override
    @SneakyThrows
    public Optional<User> findOneByEmail(String email) {
        PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_BY_EMAIL);
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next()) {
            return Optional.empty();
        }
        return Optional.of(userWithoutSongsRowMapper.rowMap(resultSet));
    }

    @SneakyThrows
    public Optional<User> findOne(int id) {
        userWithSongsMap = new HashMap<>();
        PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_WITH_SONGS_BY_ID);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            userWithSongsForOneUserRowMapper.rowMap(resultSet);
        }
        theOnlyUser.setSongs(userWithSongsMap.get(theOnlyUser));
        User result = theOnlyUser;
        theOnlyUser = null;
        userWithSongsMap.clear();
        return Optional.of(result);
    }

    @SneakyThrows
    public void save(User model) {
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, model.getFirstName());
        statement.setString(2, model.getLastName());
        statement.setString(3, model.getEmail());
        statement.setString(4, model.getHashPassword());
        ResultSet resultSet = statement.getGeneratedKeys();
        while (resultSet.next()) {
            model.setClientId(resultSet.getLong("client_id"));
        }
        statement.executeUpdate();
    }

    @SneakyThrows
    public void delete(Long id) {
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE_QUERY);
        statement.setLong(1, id);
        statement.executeUpdate();
    }

    @SneakyThrows
    public Optional<List<User>> findAll() {
        userIdWithSongsMap = new HashMap<>();
        PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USERS_WITH_SONGS);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            userWithSongsRowMapper.rowMap(resultSet);
        }
        return Optional.of(new ArrayList<>(userIdWithSongsMap.values()));
    }
}
