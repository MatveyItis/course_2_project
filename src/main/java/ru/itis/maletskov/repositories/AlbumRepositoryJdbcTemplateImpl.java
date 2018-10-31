package ru.itis.maletskov.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.itis.maletskov.models.Album;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class AlbumRepositoryJdbcTemplateImpl implements AlbumRepository {
    private JdbcTemplate jdbcTemplate;

    public AlbumRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Album> findOne(Integer id) {
        return Optional.empty();
    }

    @Override
    public void save(Album model) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Optional<List<Album>> findAll() {
        return Optional.empty();
    }
}
