package ru.itis.maletskov.repositories;

import lombok.SneakyThrows;
import ru.itis.maletskov.mappers.RowMapper;
import ru.itis.maletskov.models.Album;
import ru.itis.maletskov.models.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class AlbumRepositoryConnectionImpl implements AlbumRepository {
    private Connection connection;
    private Map<Album, List<Song>> albumWithSongsMap;
    private Map<Long, Album> albumIdWithSongsMap;
    private Album theOnlyAlbum;

    //language=SQL
    private static final String SQL_SELECT_ALBUM_WITH_SONGS_BY_ID = "select * from album " +
            "join albums_songs a on album.album_id = a.album_id " +
            "join song s2 on a.song_id = s2.song_id " +
            "where a.album_id = ?";
    //language=SQL
    private static final String SQL_SELECT_ALBUMS_WITH_SONGS = "select * from album " +
            "join albums_songs a on album.album_id = a.album_id " +
            "join song s2 on a.song_id = s2.song_id";

    public AlbumRepositoryConnectionImpl(Connection connection) {
        this.connection = connection;
        albumIdWithSongsMap = new HashMap<>();
        albumWithSongsMap = new HashMap<>();
    }

    private RowMapper<Album> albumWithoutSongsRowMapper = new RowMapper<>() {
        @SneakyThrows
        public Album rowMap(ResultSet resultSet) {
            return Album.builder()
                    .albumId(resultSet.getLong("album_id"))
                    .albumTitle(resultSet.getString("album_title"))
                    .albumYear(resultSet.getLong("album_year"))
                    .albumTracks(resultSet.getLong("album_tracks"))
                    .artistId(resultSet.getLong("artist_id"))
                    .build();
        }
    };

    private RowMapper<Album> albumWithSongsForOneAlbumRowMapper = new RowMapper<>() {
        @SneakyThrows
        @Override
        public Album rowMap(ResultSet resultSet) {
            if (albumWithSongsMap.size() == 0) {
                Album newAlbum = albumWithoutSongsRowMapper.rowMap(resultSet);
                albumWithSongsMap.put(newAlbum, new ArrayList<>());
                theOnlyAlbum = newAlbum;
            }
            Song song = Song.builder()
                    .songId(resultSet.getLong("song_id"))
                    .title(resultSet.getString("song_title"))
                    .artistId(resultSet.getLong("artist_id"))
                    .duration(resultSet.getLong("song_duration"))
                    .build();
            albumWithSongsMap.get(theOnlyAlbum).add(song);
            return theOnlyAlbum;
        }
    };

    private RowMapper<Album> albumWithSongsRowMapper = new RowMapper<>() {
        @SneakyThrows
        @Override
        public Album rowMap(ResultSet resultSet) {
            Long currentAlbumId = resultSet.getLong("album_id");
            if (!albumIdWithSongsMap.containsKey(currentAlbumId)) {
                Album newAlbum = albumWithoutSongsRowMapper.rowMap(resultSet);
                newAlbum.setAlbumSongs(new ArrayList<>());
                albumIdWithSongsMap.put(currentAlbumId, newAlbum);
            }
            Song song = Song.builder()
                    .songId(resultSet.getLong("song_id"))
                    .title(resultSet.getString("song_title"))
                    .artistId(resultSet.getLong("artist_id"))
                    .duration(resultSet.getLong("song_duration"))
                    .build();
            Album currentAlbum = albumIdWithSongsMap.get(currentAlbumId);
            List<Song> songsOfAlbum = currentAlbum.getAlbumSongs();
            songsOfAlbum.add(song);
            return currentAlbum;
        }
    };

    /*
    @SneakyThrows
    private List<Song> getSongsFromAlbum(Long id) {
        List<Song> songs = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from song " +
                "join albums_songs a on song.song_id = a.song_id and a.album_id = " + id);
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
    }*/

    @SneakyThrows
    public Optional<Album> findOne(Long id) {
        PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALBUM_WITH_SONGS_BY_ID);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            albumWithSongsForOneAlbumRowMapper.rowMap(resultSet);
        }
        theOnlyAlbum.setAlbumSongs(albumWithSongsMap.get(theOnlyAlbum));
        Album result = theOnlyAlbum;
        theOnlyAlbum = null;
        albumWithSongsMap.clear();
        return Optional.of(result);
    }

    @Override
    public void save(Album model) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<List<Album>> findAll() {
        return null;
    }
}
