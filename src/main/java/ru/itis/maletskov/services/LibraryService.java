package ru.itis.maletskov.services;

public interface LibraryService {
    void addSongToLibrary(Integer songId, Integer libraryId);
    void removeSongFromLibrary(Integer songId, Integer libraryId);
}
