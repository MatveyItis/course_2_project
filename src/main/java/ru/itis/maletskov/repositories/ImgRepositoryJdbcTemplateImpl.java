package ru.itis.maletskov.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectUpdateSemanticsDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.maletskov.models.Img;
import ru.itis.maletskov.util.ContextRowMapper;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class ImgRepositoryJdbcTemplateImpl implements ImgRepository {
    private final JdbcTemplate jdbcTemplate;

    //language=SQL
    private final String SQL_SELECT_IMG_BY_ID = "select * from img where id = ?";

    //language=SQL
    private final String SQL_INSERT = "insert into img(filename, name) values (?, ?)";

    //language=SQL
    private final String SQL_DELETE = "delete from img where id = ?";

    //language=SQL
    private final String SQL_UPDATE = "update img set (filename, name) = (?, ?) where id = ?";

    //language=SQL
    private final String SQL_SELECT_ALL = "select * from img";

    @Autowired
    public ImgRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Img> findOne(Long id) {
        Img img = jdbcTemplate.queryForObject(SQL_SELECT_IMG_BY_ID, ContextRowMapper.imgRowMapper, id);
        return img == null ? Optional.empty() : Optional.of(img);
    }

    @Override
    public Img save(Img img) {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(
                    connection -> {
                        PreparedStatement statement =
                                connection.prepareStatement(SQL_INSERT, new String[]{"id"});
                        statement.setString(1, img.getFileName());
                        statement.setString(2, img.getName());
                        return statement;
                    }, keyHolder);
            img.setId(keyHolder.getKey().longValue());
            return img;
        } catch (IncorrectUpdateSemanticsDataAccessException e) {
            throw new IncorrectUpdateSemanticsDataAccessException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            jdbcTemplate.update(SQL_DELETE, id);
        } catch (IncorrectUpdateSemanticsDataAccessException e) {
            throw new IncorrectUpdateSemanticsDataAccessException(e.getMessage());
        }
    }

    @Override
    public List<Img> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, ContextRowMapper.imgRowMapper);
    }

    @Override
    public void update(Img img) {
        try {
            jdbcTemplate.update(SQL_UPDATE, img.getName(), img.getFileName(), img.getId());
        } catch (IncorrectUpdateSemanticsDataAccessException e) {
            throw new IncorrectUpdateSemanticsDataAccessException(e.getMessage());
        }
    }
}
