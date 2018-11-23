package ru.itis.maletskov.services;

import ru.itis.maletskov.models.Song;

import java.util.List;

public interface SongService {
    List<Song> getAllSongs();
    Song getSongById(Integer id);
    List<Song> getSongsByUserId(Integer userId);
    void addSongToLibrary(Integer songId, Integer libraryId);
    void removeSongFromLibrary(Integer songId, Integer libraryId);
    void saveSong(Song song);
    List<Song> searchSongs(String songName);
}
