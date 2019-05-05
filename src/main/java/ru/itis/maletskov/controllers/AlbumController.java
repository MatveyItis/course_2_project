package ru.itis.maletskov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.maletskov.models.Album;
import ru.itis.maletskov.models.User;
import ru.itis.maletskov.services.SongService;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class AlbumController {
    private final SongService songService;

    @Autowired
    public AlbumController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("/albums")
    public String getAlbums(@AuthenticationPrincipal User user,
                            Model model) {
        List<Album> albums = songService.getAlbums();
        model.addAttribute("albums", albums);
        model.addAttribute("user", user);
        return "albums";
    }

    @PostMapping("/add_album")
    public String addAlbum(@AuthenticationPrincipal User user,
                           @RequestParam("album_title") String title,
                           @RequestParam("album_cover") MultipartFile albumCover,
                           @RequestParam("song_title") String[] songTitles,
                           @RequestParam("audio_file") MultipartFile[] audioFiles,
                           BindingResult bindingResult,
                           Model model) throws IOException {
        Album album = new Album();
        album.setOwner(user);
        album.setTitle(title);
        album.setDate(new Date());
        songService.createAlbum(album, albumCover, audioFiles, songTitles);

        return "redirect:/albums";
    }
}
