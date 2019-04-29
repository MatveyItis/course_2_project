package ru.itis.maletskov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import ru.itis.maletskov.controllers.util.ControllerUtils;
import ru.itis.maletskov.jpamodels.Img;
import ru.itis.maletskov.jpamodels.Song;
import ru.itis.maletskov.jpamodels.User;
import ru.itis.maletskov.jpamodels.dto.SongDto;
import ru.itis.maletskov.services.SongService;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
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
                              @AuthenticationPrincipal User user,
                              @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<SongDto> page = songService.songList(filter, pageable, user);
        model.addAttribute("page", page);
        model.addAttribute("filter", filter);
        model.addAttribute("url", "/feed");
        model.addAttribute("user", user);
        return "feed";
    }

    @GetMapping("/feed")
    public String feed(Model model,
                       @AuthenticationPrincipal User user,
                       @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<SongDto> page = songService.songList("", pageable, user);
        model.addAttribute("user", user);
        model.addAttribute("page", page);
        model.addAttribute("url", "/feed");
        return "feed";
    }

    @PostMapping("/add_song")
    public String addSong(@RequestParam(value = "img_file", required = false) MultipartFile imgFile,
                          @RequestParam("music_file") MultipartFile musicFile,
                          @Valid Song song,
                          BindingResult bindingResult,
                          Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            return "feed";
        } else {
            if (musicFile.isEmpty()) {
                model.addAttribute("musicFileError", "Please select audio file");
                return "feed";
            }
            saveAudioFile(song, musicFile);
            if (!imgFile.isEmpty()) {
                saveSongImageFile(song, imgFile);
            }
            songService.saveSong(song);
        }
        return "redirect:/feed";
    }

    @GetMapping("/songs/{song}/like")
    public String like(@AuthenticationPrincipal User user,
                       @PathVariable Song song,
                       RedirectAttributes redirectAttributes,
                       @RequestHeader(required = false) String referer) {
        Set<User> likes = song.getLikes();

        if (likes.contains(user)) {
            likes.remove(user);
        } else {
            likes.add(user);
        }

        songService.saveSong(song);
        UriComponents components = UriComponentsBuilder.fromHttpUrl(referer).build();

        components.getQueryParams()
                .forEach(redirectAttributes::addAttribute);

        return "redirect:" + components.getPath();
    }

    private void saveAudioFile(Song song, MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty() && !file.isEmpty()) {
            new File(audioUploadPath).mkdir();

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + file.getOriginalFilename();

            file.transferTo(new File(audioUploadPath + "/" + resultFilename));

            song.setAudioFileName(resultFilename);
        }
    }

    private void saveSongImageFile(Song song, MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty() && !file.isEmpty()) {
            new File(imgUploadPath).mkdir();

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + file.getOriginalFilename();

            file.transferTo(new File(imgUploadPath + "/" + resultFilename));

            Img img = new Img();
            img.setFileName(resultFilename);
            song.setSongImg(img);
        }
    }
}
