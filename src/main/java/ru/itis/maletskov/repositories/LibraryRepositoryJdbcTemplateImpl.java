package ru.itis.maletskov.repositories;

import lombok.SneakyThrows;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.maletskov.models.Library;
import ru.itis.maletskov.models.Song;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibraryRepositoryJdbcTemplateImpl implements LibraryRepository {
    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private static final String SQL_INSERT_SONG_INTO_LIBRARY = "insert into songs_library(library_id, song_id) values(?, ?)";

    //language=SQL
    private static final String SQL_REMOVE_SONG_FROM_LIBRARY = "delete from songs_library where (library_id = ? and song_id = ?)";

    //language=SQL
    private static final String SQL_SELECT_LIBRARY = "select * from library " +
            "left join songs_library l on library.library_id = l.library_id " +
            "left join song s2 on l.song_id = s2.song_id " +
            "where library.library_id = ?";

    //language=SQL
    private static final String SQL_SELECT_LIBRARIES = "select * from library " +
            "left join songs_library l on library.library_id = l.library_id " +
            "left join song s2 on l.song_id = s2.song_id ";


    public LibraryRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public RowMapper<Library> libraryRowMapper = (resultSet, i) -> {
        List<Song> songs = new ArrayList<>();
        Library library = null;
        do {
            if (library == null) {
                library = Library.builder()
                        .libraryId(resultSet.getInt("library_id"))
                        .clientId(resultSet.getInt("client_id"))
                        .build();
            }
            Song song = Song.builder()
                    .songId(resultSet.getInt("song_id"))
                    .title(resultSet.getString("song_title"))
                    .duration(resultSet.getInt("song_duration"))
                    .artistId(resultSet.getInt("artist_id"))
                    .songSrc(resultSet.getString("song_src"))
                    .build();
            if (song != null) {
                songs.add(song);
            }
        } while (resultSet.next());
        library.setSongs(songs);
        return library;
    };

    @SneakyThrows
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
        return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_LIBRARY, libraryRowMapper, id));
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
