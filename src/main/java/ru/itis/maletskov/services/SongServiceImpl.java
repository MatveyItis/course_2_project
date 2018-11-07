package ru.itis.maletskov.services;

import lombok.SneakyThrows;
import ru.itis.maletskov.models.Song;
import ru.itis.maletskov.repositories.SongRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SongServiceImpl implements SongService {
    private SongRepository songRepository;

    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @SneakyThrows
    @Override
    public List<Song> getAllSongs() {
        Optional<List<Song>> optionalSongs = songRepository.findAll();
        return optionalSongs.orElseGet(ArrayList::new);
    }
}
