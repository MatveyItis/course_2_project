package ru.itis.maletskov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.maletskov.jparepositories.SongRepository;
import ru.itis.maletskov.models.Album;
import ru.itis.maletskov.models.Song;
import ru.itis.maletskov.models.User;
import ru.itis.maletskov.models.dto.SongDto;
import ru.itis.maletskov.repositories.AlbumRepository;
import ru.itis.maletskov.util.ServiceUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SongService {
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    private final ServiceUtils serviceUtils;

    @Autowired
    public SongService(SongRepository songRepository,
                       AlbumRepository albumRepository,
                       ServiceUtils serviceUtils) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
        this.serviceUtils = serviceUtils;
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

    public void deleteSong(Song song, User user) {
        if (song.getAuthor().getId().equals(user.getId())) {
            songRepository.deleteById(song.getId());
        }
    }

    public void saveSong(Song song) {
        songRepository.save(song);
    }

    public Album createAlbum(Album album,
                             MultipartFile albumCover,
                             MultipartFile[] audioFiles,
                             String[] songTitles) throws IOException {
        Set<Song> songs = new HashSet<>();
        album.setImg(serviceUtils.saveImageFile(albumCover));
        if (audioFiles.length != songTitles.length) {
            return null;
        } else {
            for (int i = 0; i < audioFiles.length; i++) {
                Song song = new Song();
                song.setAuthor(album.getOwner());
                song.setTitle(songTitles[i]);
                song.setSongImg(album.getImg());
                serviceUtils.saveAudioFile(song, audioFiles[i]);
                songs.add(song);
            }
        }
        album.setSongs(songs);
        return albumRepository.save(album);
    }

    public List<Album> getAlbums() {
        return albumRepository.findAll();
    }
}
