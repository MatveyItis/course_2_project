package ru.itis.maletskov.repositories;

import lombok.SneakyThrows;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.itis.maletskov.mappers.LibraryRowMapper;
import ru.itis.maletskov.models.Library;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static ru.itis.maletskov.mappers.LibraryRowMapper.libraryWithSongsMap;
import static ru.itis.maletskov.mappers.LibraryRowMapper.theOnlyLibrary;

public class LibraryRepositoryJdbcTemplateImpl implements LibraryRepository {
    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private final String SQL_INSERT_SONG_INTO_LIBRARY = "insert into songs_library(library_id, song_id) values(?, ?)";

    //language=SQL
    private final String SQL_REMOVE_SONG_FROM_LIBRARY = "delete from songs_library where (library_id = ? and song_id = ?)";

    //language=SQL
    private final String SQL_SELECT_LIBRARY_WITH_SONGS = "select library.library_id, client_id, s.song_id, " +
            "s.song_title, s.song_duration, s.song_src, a1.artist_id as artist_id, " +
            "a1.nickname as nickname, a1.first_name as first_name, " +
            "a1.last_name as last_name, a1.birthday as artist_birthday, " +
            "a1.img_src as artist_img_src from library " +
            "join songs_library as sl on library.library_id = sl.library_id " +
            "join song as s on sl.song_id = s.song_id " +
            "join artist as a1 on s.artist_id = a1.artist_id " +
            "where library.library_id = ?";

    //language=SQL
    private final String SQL_SELECT_LIBRARIES = "select library.library_id, client_id, s.song_id, " +
            "s.song_title, s.song_duration, s.song_src, a1.artist_id as artist_id, " +
            "a1.nickname as nickname, a1.first_name as first_name, " +
            "a1.last_name as last_name, a1.birthday as artist_birthday, " +
            "a1.img_src as artist_img_src from library " +
            "join songs_library as sl on library.library_id = sl.library_id " +
            "join song as s on sl.song_id = s.song_id " +
            "join artist as a1 on s.artist_id = a1.artist_id";

    //language=SQL
    private final String SQL_DELETE_LIBRARY = "delete from songs_library where library_id = ?";

    public LibraryRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public LibraryRepositoryJdbcTemplateImpl() {}

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
        List<Library> libraryList = jdbcTemplate.query(SQL_SELECT_LIBRARY_WITH_SONGS, LibraryRowMapper.libraryRowMapper, id);
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
        return Optional.of(jdbcTemplate.query(SQL_SELECT_LIBRARIES, LibraryRowMapper.libraryRowMapper));
    }
}
