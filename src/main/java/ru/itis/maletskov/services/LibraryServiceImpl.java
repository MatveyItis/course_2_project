package ru.itis.maletskov.services;

import lombok.SneakyThrows;
import ru.itis.maletskov.repositories.LibraryRepository;

public class LibraryServiceImpl implements LibraryService {
    private LibraryRepository libraryRepository;

    public LibraryServiceImpl(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    @SneakyThrows
    @Override
    public void addSongToLibrary(Integer songId, Integer libraryId) {
        libraryRepository.saveSongToLibrary(songId, libraryId);
    }

    @SneakyThrows
    @Override
    public void removeSongFromLibrary(Integer songId, Integer libraryId) {
        libraryRepository.removeSongFromLibrary(songId, libraryId);
    }
}
