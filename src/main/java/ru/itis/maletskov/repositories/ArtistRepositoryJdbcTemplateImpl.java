package ru.itis.maletskov.repositories;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itis.maletskov.models.Artist;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Repository
public class ArtistRepositoryJdbcTemplateImpl implements ArtistRepository {
    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private final String SQL_SELECT_ALL_ARTISTS = "select * from artist";

    @Autowired
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

    @Override
    public void update(Artist model) {

    }
}
