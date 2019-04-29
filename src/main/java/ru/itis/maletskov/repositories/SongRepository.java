package ru.itis.maletskov.repositories;

import ru.itis.maletskov.jpamodels.Song;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends CrudRepository<Song> {
    Optional<List<Song>> searchByName(String songName);
}
