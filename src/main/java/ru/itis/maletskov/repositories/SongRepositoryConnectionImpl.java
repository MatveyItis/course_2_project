package ru.itis.maletskov.repositories;

import ru.itis.maletskov.models.Song;

import java.util.List;
import java.util.Optional;

public class SongRepositoryConnectionImpl implements SongRepository {
    @Override
    public Optional<Song> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Song model) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<List<Song>> findAll() {
        return Optional.empty();
    }
}
