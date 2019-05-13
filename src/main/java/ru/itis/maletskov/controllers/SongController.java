package ru.itis.maletskov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import ru.itis.maletskov.models.Song;
import ru.itis.maletskov.models.User;
import ru.itis.maletskov.models.dto.SongDto;
import ru.itis.maletskov.services.SongService;
import ru.itis.maletskov.services.UserService;
import ru.itis.maletskov.util.ServiceUtils;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

@Controller
public class SongController {
    private final SongService songService;
    private final UserService userService;
    private final ServiceUtils serviceUtils;

    @Autowired
    public SongController(SongService songService,
                          UserService userService,
                          ServiceUtils serviceUtils) {
        this.songService = songService;
        this.userService = userService;
        this.serviceUtils = serviceUtils;
    }

    @GetMapping("/search_song/filter")
    public String searchByTag(@RequestParam(required = false, defaultValue = "") String filter,
                              Model model,
                              @AuthenticationPrincipal User user,
                              @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        User usr = userService.findById(user.getId());
        Page<SongDto> page = songService.songList(filter, pageable, usr);
        model.addAttribute("page", page);
        model.addAttribute("filter", filter);
        model.addAttribute("url", "/feed");
        model.addAttribute("user", usr);
        model.addAttribute("addedSongs", usr.getAddedSongs());
        return "feed";
    }

    @GetMapping("/feed")
    public String feed(Model model,
                       @AuthenticationPrincipal User user,
                       @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        User usr = userService.findById(user.getId());
        Page<SongDto> page = songService.songList("", pageable, usr);
        model.addAttribute("user", usr);
        model.addAttribute("addedSongs", usr.getAddedSongs());
        model.addAttribute("page", page);
        model.addAttribute("url", "/feed");
        return "feed";
    }

    @PreAuthorize("hasAuthority('SINGER')")
    @PostMapping("/add_song")
    public String addSong(@RequestParam(value = "img_file", required = false) MultipartFile imgFile,
                          @RequestParam("music_file") MultipartFile musicFile,
                          @Valid Song song,
                          BindingResult bindingResult,
                          Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ServiceUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            return "feed";
        } else {
            if (musicFile.isEmpty()) {
                model.addAttribute("musicFileError", "Please select audio file");
                return "feed";
            }
            serviceUtils.saveAudioFile(song, musicFile);
            if (!imgFile.isEmpty()) {
                song.setSongImg(serviceUtils.saveImageFile(imgFile));
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

    /*@GetMapping("/songs/{song}/like")
    @ResponseBody
    public Boolean likeSong(@AuthenticationPrincipal User user,
                            @PathVariable Song song) {
        Set<User> likes = song.getLikes();
        if (likes.contains(user)) {
            likes.remove(user);
            return true;
        } else {
            likes.add(user);
            return false;
        }
    }
*/
    @GetMapping("/favourite/{user}")
    public String favouriteUserSongs(@PathVariable User user,
                                     Model model) {
        Set<Song> songs = user.getAddedSongs();
        if (!songs.isEmpty()) {
            model.addAttribute("songs", songs);
        }
        return "favourite";
    }

    @GetMapping("/favourite")
    public String favourite(@AuthenticationPrincipal User user) {
        return "redirect:/favourite/" + user.getId();
    }
}
