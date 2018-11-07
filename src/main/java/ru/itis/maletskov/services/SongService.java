package ru.itis.maletskov.services;

import ru.itis.maletskov.models.Song;

import java.util.List;

public interface SongService {
    List<Song> getAllSongs();
}
