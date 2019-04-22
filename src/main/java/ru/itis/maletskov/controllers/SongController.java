package ru.itis.maletskov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.maletskov.jpamodels.Img;
import ru.itis.maletskov.jpamodels.Song;
import ru.itis.maletskov.jpamodels.User;
import ru.itis.maletskov.services.SongService;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class SongController {
    private final SongService songService;

    @Value("${upload.song.audio.path}")
    private String audioUploadPath;

    @Value("${upload.img.path}")
    private String imgUploadPath;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("/search_song/filter")
    public String searchByTag(@RequestParam(required = false, defaultValue = "") String filter,
                              Model model,
                              @AuthenticationPrincipal User user) {
        List<Song> songs = songService.songList(filter);
        model.addAttribute("songs", songs);
        model.addAttribute("filter", filter);
        return "feed";
    }

    @GetMapping("/feed")
    public String feed(Model model, @AuthenticationPrincipal User user) {
        List<Song> songs = songService.songList();
        model.addAttribute("user", user);
        model.addAttribute("songs", songs);
        return "feed";
    }

    @PostMapping("/add_song")
    public String addSong(@RequestParam(value = "img_file", required = false) MultipartFile imgFile,
                          @RequestParam("music_file") MultipartFile musicFile,
                          @Valid Song song,
                          BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            System.out.println("Has errors" + bindingResult.getAllErrors().toString());
        } else {
            saveAudioFile(song, musicFile);
            if (!imgFile.isEmpty()) {
                saveSongImageFile(song, imgFile);
            }
            songService.saveSong(song);
        }
        return "redirect:/feed";
    }

    private void saveAudioFile(Song song, MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            /*File uploadDir = new File(audioUploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }*/
            new File(audioUploadPath).mkdir();

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "_" + file.getOriginalFilename();

            file.transferTo(new File(audioUploadPath + "/" + resultFilename));

            song.setAudioFileName(resultFilename);
        }
    }

    private void saveSongImageFile(Song song, MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            /*File uploadDir = new File(imgUploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }*/
            new File(imgUploadPath).mkdir();

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "__" + file.getOriginalFilename();

            file.transferTo(new File(imgUploadPath + "/" + resultFilename));

            Img img = new Img();
            img.setFileName(resultFilename);
            song.setSongImg(img);
        }
    }
}
