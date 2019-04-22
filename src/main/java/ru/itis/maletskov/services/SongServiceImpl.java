package ru.itis.maletskov.services;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import ru.itis.maletskov.models.Song;
import ru.itis.maletskov.repositories.SongRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Service
public class SongServiceImpl {
    private SongRepository songRepository;

    public SongServiceImpl() {
    }

    @Autowired
    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @SneakyThrows
    public List<Song> getAllSongs() {
        Optional<List<Song>> optionalSongs = songRepository.findAll();
        return optionalSongs.orElseGet(ArrayList::new);
    }

    @SneakyThrows
    public Song getSongById(Integer id) {
        Optional<Song> optionalSong = songRepository.findOne(id);
        return optionalSong.orElse(null);
    }

    public void saveSong(Song song) {
        songRepository.save(song);
    }

    public List<Song> searchSongs(String songName) {
        Optional<List<Song>> optionalSongs = songRepository.searchByName(songName);
        return optionalSongs.orElseGet(ArrayList::new);
    }
}
