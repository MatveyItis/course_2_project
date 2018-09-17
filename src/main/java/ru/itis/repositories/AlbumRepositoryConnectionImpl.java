package ru.itis.repositories;

import ru.itis.models.Album;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class AlbumRepositoryConnectionImpl implements AlbumRepository {
    private Connection connection;

    public AlbumRepositoryConnectionImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Album> findOne(int id) {
        return Optional.empty();
    }

    @Override
    public void save(Album model) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Optional<List<Album>> findAll() {
        return null;
    }
}
