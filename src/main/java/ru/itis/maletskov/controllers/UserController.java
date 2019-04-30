package ru.itis.maletskov.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import ru.itis.maletskov.controllers.util.ControllerUtils;
import ru.itis.maletskov.jpamodels.Song;
import ru.itis.maletskov.jpamodels.User;
import ru.itis.maletskov.services.UserService;

import java.util.Map;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registrationPage(@AuthenticationPrincipal User authUser,
                                   Model model) {
        if (authUser != null) {
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("UC#profilePage").build();
        }

        model.addAttribute("user", new User());
        return "registration";
    }

    @GetMapping("/login")
    public String loginPage(@AuthenticationPrincipal User authUser) {
        if (authUser != null) {
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("UC#profilePage").build();
        }
        return "login";
    }

    @PostMapping("/registration")
    public String registerUser(@Validated @ModelAttribute("user") User user,
                               @RequestParam("password2") String password2,
                               Model model,
                               BindingResult bindingResult) {
        boolean isConfirmEmpty = StringUtils.isEmpty(password2);
        boolean passwordsError = false;
        if (isConfirmEmpty) {
            model.addAttribute("password2Error", "Password confirmation cannot be empty");
        }
        if (user.getPassword() != null && !user.getPassword().equals(password2)) {
            passwordsError = true;
            model.addAttribute("passwordError", "Passwords are different");
        }
        if (isConfirmEmpty || bindingResult.hasErrors() || passwordsError) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            return "registration";
        }
        if (!userService.addUser(user)) {
            model.addAttribute("usernameError", "User exists!");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/user_profile")
    public String profilePage(@AuthenticationPrincipal User user,
                              Model model) {
        model.addAttribute("user", user);
        model.addAttribute("isCurrentUser", true);
        return "user_profile";
    }

    @GetMapping("/user_profile/{user}")
    public String userProfile(@PathVariable("user") User pathUser,
                              @AuthenticationPrincipal User user,
                              Model model) {
        if (pathUser == null) {
            model.addAttribute("isCurrentUser", true);
            model.addAttribute("user", user);
        } else {
            if (user.getId().equals(pathUser.getId())) {
                model.addAttribute("isCurrentUser", true);
                model.addAttribute("user", user);
            } else {
                model.addAttribute("isCurrentUser", false);
                model.addAttribute("user", pathUser);
            }
        }
        model.addAttribute("userChannel", pathUser);
        model.addAttribute("isSubscriber", pathUser.getSubscribers().contains(user));
        return "user_profile";
    }

    @GetMapping("/subscribe/{user}")
    public String subscribe(@AuthenticationPrincipal User currentUser,
                            @PathVariable User user) {
        userService.subscribe(currentUser, user);
        return "redirect:/user_profile/" + user.getId();
    }

    @GetMapping("/unsubscribe/{user}")
    public String unsubscribe(@AuthenticationPrincipal User currentUser,
                              @PathVariable User user) {
        userService.unsubscribe(currentUser, user);
        return "redirect:/user_profile/" + user.getId();
    }

    @GetMapping("/user/{type}/{user}")
    public String subscriptionsList(Model model,
                                    @PathVariable User user,
                                    @PathVariable String type) {
        model.addAttribute("userChannel", user);
        model.addAttribute("type", type);

        if ("subscriptions".equals(type)) {
            model.addAttribute("users", user.getSubscriptions());
        } else {
            model.addAttribute("users", user.getSubscribers());
        }
        return "subscriptions";
    }

    @PostMapping("/user/{user}/add_song")
    public String addSongToFavourite(@RequestParam("songId") Song song,
                                     @PathVariable User user,
                                     Model model) {
        if (song != null && user != null) {
            userService.addSongToFavourite(song, user);
            return "redirect:/feed";
        } else {
            model.addAttribute("songIdError", song.getId());
            model.addAttribute("addingError", "Error with adding song to favourite. Please try later.");
            return "feed";
        }
    }
}