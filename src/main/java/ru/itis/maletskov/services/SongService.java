package ru.itis.maletskov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.maletskov.jpamodels.Song;
import ru.itis.maletskov.jparepositories.UserRepository;
import ru.itis.maletskov.jparepositories.SongRepository;

import java.util.List;

@Service
public class SongService {
    private final UserRepository userRepository;
    private final SongRepository songRepository;

    @Autowired
    public SongService(UserRepository userRepository,
                       SongRepository songRepository) {
        this.userRepository = userRepository;
        this.songRepository = songRepository;
    }

    public List<Song> songList(String filter) {
        if (!filter.isEmpty()) {
            return songRepository.findByTag(filter);
        } else {
            return songRepository.findAll();
        }
    }

    public List<Song> songList() {
        return songRepository.findAll();
    }

    public void saveSong(Song song) {
        songRepository.save(song);
    }
}
