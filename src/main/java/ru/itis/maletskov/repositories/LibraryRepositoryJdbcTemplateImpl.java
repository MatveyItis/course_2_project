package ru.itis.maletskov.repositories;

import lombok.SneakyThrows;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
    private static final String SQL_SELECT_LIBRARY = "select * from library " +
            "join songs_library l on library.library_id = l.library_id " +
            "join song s2 on l.song_id = s2.song_id " +
            "where library.library_id = ?";

    //language=SQL
    private static final String SQL_SELECT_LIBRARY_WITH_SONGS = "select * from library " +
            "left join songs_library l on library.library_id = l.library_id " +
            "left join song s2 on l.song_id = s2.song_id " +
            "where library.library_id = ?";

    //language=SQL
    private static final String SQL_SELECT_LIBRARIES = "select * from library " +
            "join songs_library l on library.library_id = l.library_id " +
            "join song s2 on l.song_id = s2.song_id ";


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
                .artistId(resultSet.getInt("artist_id"))
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

    }

    @SneakyThrows
    @Override
    public Optional<List<Library>> findAll() {
        return Optional.of(jdbcTemplate.query(SQL_SELECT_LIBRARIES, libraryRowMapper));
    }
}
