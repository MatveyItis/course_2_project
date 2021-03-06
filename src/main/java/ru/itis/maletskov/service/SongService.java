package ru.itis.maletskov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.maletskov.dto.SongDto;
import ru.itis.maletskov.model.Album;
import ru.itis.maletskov.model.Song;
import ru.itis.maletskov.model.User;
import ru.itis.maletskov.repository.AlbumRepository;
import ru.itis.maletskov.repository.SongRepository;
import ru.itis.maletskov.util.ServiceUtils;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SongService {
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    private final ServiceUtils serviceUtils;

    @Autowired
    private EntityManagerFactory emf;

    @Value("${upload.song.path}")
    private String audioUploadPath;

    @Value("${upload.img.path}")
    private String imgUploadPath;

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

    public void deleteSong(Song song) throws IOException {
        songRepository.deleteSongById(song.getId());
        String deletedImgFileName = song.getSongImg().getFileName();
        Files.deleteIfExists(Paths.get(URI.create("file://" + imgUploadPath + "/" + deletedImgFileName)));
        Files.deleteIfExists(Paths.get(URI.create("file://" + audioUploadPath + "/" + song.getAudioFileName())));
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
