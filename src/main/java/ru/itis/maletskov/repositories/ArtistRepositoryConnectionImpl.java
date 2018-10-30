package ru.itis.maletskov.repositories;

import ru.itis.maletskov.models.Artist;

import java.util.List;
import java.util.Optional;

public class ArtistRepositoryConnectionImpl implements ArtistRepository {


    @Override
    public Optional<Artist> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Artist model) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<List<Artist>> findAll() {
        return Optional.empty();
    }
}
