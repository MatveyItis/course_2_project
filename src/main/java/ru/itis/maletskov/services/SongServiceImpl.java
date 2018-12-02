package ru.itis.maletskov.services;

import lombok.SneakyThrows;
import ru.itis.maletskov.models.Library;
import ru.itis.maletskov.models.Song;
import ru.itis.maletskov.repositories.LibraryRepository;
import ru.itis.maletskov.repositories.SongRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SongServiceImpl implements SongService {
    private SongRepository songRepository;
    private LibraryRepository libraryRepository;

    public SongServiceImpl() {}

    public SongServiceImpl(SongRepository songRepository, LibraryRepository libraryRepository) {
        this.songRepository = songRepository;
        this.libraryRepository = libraryRepository;
    }

    @SneakyThrows
    @Override
    public List<Song> getAllSongs() {
        Optional<List<Song>> optionalSongs = songRepository.findAll();
        return optionalSongs.orElseGet(ArrayList::new);
    }

    @SneakyThrows
    @Override
    public Song getSongById(Integer id) {
        Optional<Song> optionalSong = songRepository.findOne(id);
        return optionalSong.orElse(null);
    }

    @Override
    public List<Song> getSongsByUserId(Integer userId) {
        Optional<Library> optionalSongs = libraryRepository.findOne(userId);
        return optionalSongs.map(Library::getSongs).orElse(new ArrayList<>());
    }

    @Override
    public void addSongToLibrary(Integer songId, Integer libraryId) {
        libraryRepository.saveSongToLibrary(songId, libraryId);
    }

    @Override
    public void removeSongFromLibrary(Integer songId, Integer libraryId) {
        libraryRepository.removeSongFromLibrary(songId, libraryId);
    }

    @Override
    public void saveSong(Song song) {
        songRepository.save(song);
    }

    @Override
    public List<Song> searchSongs(String songName) {
        Optional<List<Song>> optionalSongs = songRepository.searchByName(songName);
        return optionalSongs.orElseGet(ArrayList::new);
    }
}
