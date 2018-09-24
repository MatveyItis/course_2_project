package ru.itis.repositories;

import lombok.SneakyThrows;
import ru.itis.mappers.RowMapper;
import ru.itis.models.Album;
import ru.itis.models.Artist;
import ru.itis.models.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlbumRepositoryConnectionImpl implements AlbumRepository {
    private Connection connection;

    //language=SQL
    private static final String SQL_FIND_ONE_QUERY = "select * from album where album_id = ?";
    //language=SQL
    private static final String SQL_FIND_ALL_SONGS_FROM_ALBUM = "";

    public AlbumRepositoryConnectionImpl(Connection connection) {
        this.connection = connection;
    }

    private RowMapper<Album> albumRowMapper = new RowMapper<Album>() {
        @SneakyThrows
        public Album rowMap(ResultSet resultSet) {
            return Album.builder()
                    .albumId(resultSet.getLong("album_id"))
                    .albumTitle(resultSet.getString("album_title"))
                    .albumYear(resultSet.getLong("album_year"))
                    .albumTracks(resultSet.getLong("album_tracks"))
                    .artistId(resultSet.getLong("artist_id"))
                    .albumSongs(getSongsFromAlbum(resultSet.getLong("album_id")))
                    .build();
        }
    };

    @SneakyThrows
    private List<Song> getSongsFromAlbum(Long id) {
        List<Song> songs = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from song " +
                "join albums_songs a on song.song_id = a.song_id and a.album_id = " + 1);
        while (resultSet.next()) {
            Song song = Song.builder()
                    .songId(resultSet.getLong("song_id"))
                    .title(resultSet.getString("song_title"))
                    .duration(resultSet.getLong("song_duration"))
                    .artistId(resultSet.getLong("artist_id"))
                    .build();
            songs.add(song);
        }
        return songs;
    }

    @SneakyThrows
    public Optional<Album> findOne(int id) {
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_ONE_QUERY);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return Optional.of(albumRowMapper.rowMap(resultSet));
    }

    @Override
    public void save(Album model) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Optional<List<Album>> findAll() {
        return null;
    }
}
