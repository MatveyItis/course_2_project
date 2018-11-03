package ru.itis.maletskov.repositories;

import ru.itis.maletskov.models.Library;

public interface LibraryRepository extends CrudRepository<Library> {
    void saveSongToLibrary(Integer songId, Integer libraryId);
    void removeSongFromLibrary(Integer songId, Integer libraryId);
}
