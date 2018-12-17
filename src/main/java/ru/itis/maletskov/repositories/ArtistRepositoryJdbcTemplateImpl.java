package ru.itis.maletskov.repositories;

import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.itis.maletskov.mappers.ContextRowMapper;
import ru.itis.maletskov.models.Artist;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class ArtistRepositoryJdbcTemplateImpl implements ArtistRepository {
    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private final String SQL_SELECT_ALL_ARTISTS = "select * from artist";

    public ArtistRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Artist> findOne(Integer id) {
        return Optional.empty();
    }

    @Override
    public void save(Artist model) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Optional<List<Artist>> findAll() {
        return Optional.of(jdbcTemplate.query(SQL_SELECT_ALL_ARTISTS, ContextRowMapper.artistRowMapper));
    }

    @Override
    public void update(Artist model) {

    }
}
