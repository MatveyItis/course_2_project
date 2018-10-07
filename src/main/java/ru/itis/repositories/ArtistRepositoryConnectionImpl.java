package ru.itis.repositories;

import lombok.SneakyThrows;
import ru.itis.mappers.RowMapper;
import ru.itis.models.Album;
import ru.itis.models.Artist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.*;

public class ArtistRepositoryConnectionImpl implements ArtistRepository {
    private Connection connection;
    private Map<Artist, List<Album>> artistWithAlbumsMap;
    private Map<Long, Artist> artistIdWithAlbumsMap;
    private Artist theOnlyArtist;

    //language=SQL
    private final static String SQL_DELETE_QUERY = "delete from artist where artist_id = ?";

    //language=SQL
    private final static String SQL_INSERT_QUERY = "insert into artist(last_name, first_name, genre_id) " +
            "values (?, ?, ?)";

    //language=SQL
    private static final String SQL_SELECT_ARTIST_WITH_ALBUMS_BY_ID = "select * from artist " +
            "join album a on artist.artist_id = a.artist_id " +
            "where a.artist_id = ?";

    //language=SQL
    private static final String SQL_SELECT_ARTISTS_WITH_ALBUMS = "select * from artist " +
            "join album a on artist.artist_id = a.artist_id";


    public ArtistRepositoryConnectionImpl(Connection connection) {
        this.connection = connection;
    }

    private RowMapper<Artist> artistWithoutAlbumsRowMapper = new RowMapper<>() {
        @SneakyThrows
        public Artist rowMap(ResultSet resultSet) {
            return Artist.builder()
                    .artistId(resultSet.getLong("artist_id"))
                    .nickname(resultSet.getString("nickname"))
                    .firstName(resultSet.getString("first_name"))
                    .lastName(resultSet.getString("last_name"))
                    .birthday((LocalDate) resultSet.getObject("birthday"))
                    .genreId(resultSet.getLong("genre_id"))
                    .build();
        }
    };

    private RowMapper<Artist> artistWithAlbumsForOneArtistRowMapper = new RowMapper<Artist>() {
        @SneakyThrows
        @Override
        public Artist rowMap(ResultSet resultSet) {
            if (artistWithAlbumsMap.size() == 0) {
                Artist newArtist = artistWithoutAlbumsRowMapper.rowMap(resultSet);
                artistWithAlbumsMap.put(newArtist, new ArrayList<>());
                theOnlyArtist = newArtist;
            }
            Album album = Album.builder()
                    .albumId(resultSet.getLong("album_id"))
                    .albumTitle(resultSet.getString("album_title"))
                    .albumYear(resultSet.getLong("album_year"))
                    .artistId(resultSet.getLong("artist_id"))
                    .build();
            artistWithAlbumsMap.get(theOnlyArtist).add(album);
            return theOnlyArtist;
        }
    };

    private RowMapper<Artist> artistWithAlbumsRowMapper = new RowMapper<Artist>() {
        @SneakyThrows
        @Override
        public Artist rowMap(ResultSet resultSet) {
            Long currentArtistId = resultSet.getLong("artist_id");
            if (!artistIdWithAlbumsMap.containsKey(currentArtistId)) {
                Artist newArtist = artistWithoutAlbumsRowMapper.rowMap(resultSet);
                newArtist.setAlbums(new ArrayList<>());
                artistIdWithAlbumsMap.put(currentArtistId, newArtist);
            }
            //надо добавить реализацию с добавлением песен в альбом
            //чтобы автоматом все сразу заполнялось
            //использовать реализацию репозитория?
            //можно в принципе, почему нет
            //а пока останется так как есть
            //потом придется все рефакторить, уф
            Album album = Album.builder()
                    .albumId(resultSet.getLong("album_id"))
                    .albumTitle(resultSet.getString("album_title"))
                    .albumYear(resultSet.getLong("album_year"))
                    .artistId(resultSet.getLong("artist_id"))
                    .build();
            Artist currentArtist = artistIdWithAlbumsMap.get(currentArtistId);
            List<Album> albumsOfArtist = currentArtist.getAlbums();
            albumsOfArtist.add(album);
            return currentArtist;
        }
    };

    @SneakyThrows
    public Optional<Artist> findOne(Long artistId) {
        artistWithAlbumsMap = new HashMap<>();
        PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ARTIST_WITH_ALBUMS_BY_ID);
        statement.setLong(1, artistId);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            artistWithAlbumsForOneArtistRowMapper.rowMap(resultSet);
        }
        theOnlyArtist.setAlbums(artistWithAlbumsMap.get(theOnlyArtist));
        Artist result = theOnlyArtist;
        theOnlyArtist = null;
        artistWithAlbumsMap.clear();
        return Optional.of(result);
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
    public void delete(Long artistId) {
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE_QUERY);
        statement.setLong(1, artistId);
        statement.executeUpdate();
    }

    @SneakyThrows
    public Optional<List<Artist>> findAll() {
       artistIdWithAlbumsMap = new HashMap<>();
       PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ARTISTS_WITH_ALBUMS);

       ResultSet resultSet = statement.executeQuery();
       while (resultSet.next()) {
           artistWithAlbumsRowMapper.rowMap(resultSet);
       }
       List<Artist> list = new ArrayList<>(artistIdWithAlbumsMap.values());
       return Optional.of(list);
    }

}
