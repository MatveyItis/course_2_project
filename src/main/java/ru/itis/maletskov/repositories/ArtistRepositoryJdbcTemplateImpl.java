package ru.itis.maletskov.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.itis.maletskov.models.Artist;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class ArtistRepositoryJdbcTemplateImpl implements ArtistRepository {
    private JdbcTemplate jdbcTemplate;

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
        return Optional.empty();
    }
}
