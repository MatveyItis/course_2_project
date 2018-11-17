package ru.itis.maletskov.repositories;

import lombok.SneakyThrows;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.maletskov.models.Artist;
import ru.itis.maletskov.models.Library;
import ru.itis.maletskov.models.Song;

import javax.sql.DataSource;
import java.util.*;

public class LibraryRepositoryJdbcTemplateImpl implements LibraryRepository {
    private JdbcTemplate jdbcTemplate;
    private Map<Library, List<Song>> libraryWithSongsMap = new HashMap<>();
    private Library theOnlyLibrary;

    //language=SQL
    private static final String SQL_INSERT_SONG_INTO_LIBRARY = "insert into songs_library(library_id, song_id) values(?, ?)";

    //language=SQL
    private static final String SQL_REMOVE_SONG_FROM_LIBRARY = "delete from songs_library where (library_id = ? and song_id = ?)";

    //language=SQL
    private static final String SQL_SELECT_LIBRARY_WITH_SONGS = "select library.library_id, client_id, s.song_id, " +
            "s.song_title, s.song_duration, s.song_src, a1.artist_id as artist_id, " +
            "a1.nickname as nickname, a1.first_name as first_name, " +
            "a1.last_name as last_name, a1.birthday as artist_birthday, " +
            "a1.img_src as artist_img_src from library " +
            "join songs_library as sl on library.library_id = sl.library_id " +
            "join song as s on sl.song_id = s.song_id " +
            "join artist as a1 on s.artist_id = a1.artist_id " +
            "where library.library_id = ?";

    //language=SQL
    private static final String SQL_SELECT_LIBRARIES = "select library.library_id, client_id, s.song_id, " +
            "s.song_title, s.song_duration, s.song_src, a1.artist_id as artist_id, " +
            "a1.nickname as nickname, a1.first_name as first_name, " +
            "a1.last_name as last_name, a1.birthday as artist_birthday, " +
            "a1.img_src as artist_img_src from library " +
            "join songs_library as sl on library.library_id = sl.library_id " +
            "join song as s on sl.song_id = s.song_id " +
            "join artist as a1 on s.artist_id = a1.artist_id";

    //language=SQL
    private static final String SQL_DELETE_LIBRARY = "delete from songs_library where library_id = ?";


    public LibraryRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public RowMapper<Library> libraryWithoutSongsRowMapper = (resultSet, i) -> Library.builder()
            .clientId(resultSet.getInt("client_id"))
            .libraryId(resultSet.getInt("library_id"))
            .build();

    public RowMapper<Library> libraryRowMapper = (resultSet, i) -> {
        if (libraryWithSongsMap.size() == 0) {
            Library newLibrary = libraryWithoutSongsRowMapper.mapRow(resultSet, i);
            libraryWithSongsMap.put(newLibrary, new ArrayList<>());
            theOnlyLibrary = newLibrary;
        }
        Song song = Song.builder()
                .songId(resultSet.getInt("song_id"))
                .title(resultSet.getString("song_title"))
                .duration(resultSet.getInt("song_duration"))
                .artist(Artist.builder()
                        .artistId(resultSet.getInt("artist_id"))
                        .nickname(resultSet.getString("nickname"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .birthday(resultSet.getDate("artist_birthday"))
                        .artistImgSrc(resultSet.getString("artist_img_src"))
                        .build())
                .songSrc(resultSet.getString("song_src"))
                .build();
        if (song.getSongId() != 0) {
            libraryWithSongsMap.get(theOnlyLibrary).add(song);
        }
        return theOnlyLibrary;
    };

    @Override
    public void saveSongToLibrary(Integer songId, Integer libraryId) {
        jdbcTemplate.update(SQL_INSERT_SONG_INTO_LIBRARY, libraryId, songId);
    }

    @SneakyThrows
    @Override
    public void removeSongFromLibrary(Integer songId, Integer libraryId) {
        jdbcTemplate.update(SQL_REMOVE_SONG_FROM_LIBRARY, libraryId, songId);
    }

    @SneakyThrows
    @Override
    public Optional<Library> findOne(Integer id) {
        List<Library> libraryList = jdbcTemplate.query(SQL_SELECT_LIBRARY_WITH_SONGS, libraryRowMapper, id);
        if (libraryList.size() != 0) {
            Library currentLibrary = theOnlyLibrary;
            theOnlyLibrary = null;
            currentLibrary.setSongs(libraryWithSongsMap.get(currentLibrary));
            libraryWithSongsMap = null;
            libraryWithSongsMap = new HashMap<>();
            return Optional.of(currentLibrary);
        } else {
            return Optional.empty();
        }
    }

    @SneakyThrows
    @Override
    public void save(Library model) {

    }

    @SneakyThrows
    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(SQL_DELETE_LIBRARY, id);
    }

    @SneakyThrows
    @Override
    public Optional<List<Library>> findAll() {
        return Optional.of(jdbcTemplate.query(SQL_SELECT_LIBRARIES, libraryRowMapper));
    }
}
