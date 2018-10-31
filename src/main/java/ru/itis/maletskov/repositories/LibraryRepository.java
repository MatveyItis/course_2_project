package ru.itis.maletskov.repositories;

import ru.itis.maletskov.models.Library;
import ru.itis.maletskov.models.Song;

public interface LibraryRepository extends CrudRepository<Library> {
    void saveSongToLibrary(Song song, Library library);
}
