package ru.itis.maletskov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.itis.maletskov.jpamodels.Song;
import ru.itis.maletskov.jpamodels.User;
import ru.itis.maletskov.jpamodels.dto.SongDto;
import ru.itis.maletskov.jparepositories.SongRepository;

@Service
public class SongService {
    private final SongRepository songRepository;

    @Autowired
    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }


    public Page<SongDto> songList(String filter, Pageable pageable, User user) {
        if (filter != null && !filter.isEmpty()) {
            return songRepository.findByTag(filter, pageable, user);
        } else {
            return songRepository.findAll(pageable, user);
        }
    }

    public Page<SongDto> songListForUser(Pageable pageable, User currentUser, User author) {
        return songRepository.findByUser(pageable, author, currentUser);
    }

    public void saveSong(Song song) {
        songRepository.save(song);
    }
}
