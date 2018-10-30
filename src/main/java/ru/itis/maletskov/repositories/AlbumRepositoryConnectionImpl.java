package ru.itis.maletskov.repositories;

import ru.itis.maletskov.models.Album;

import java.util.List;
import java.util.Optional;

public class AlbumRepositoryConnectionImpl implements AlbumRepository {

    @Override
    public Optional<Album> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Album model) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<List<Album>> findAll() {
        return Optional.empty();
    }
}
