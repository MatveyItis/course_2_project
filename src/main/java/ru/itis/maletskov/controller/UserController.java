package ru.itis.maletskov.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import ru.itis.maletskov.form.UserForm;
import ru.itis.maletskov.model.Song;
import ru.itis.maletskov.model.User;
import ru.itis.maletskov.service.UserService;
import ru.itis.maletskov.util.ServiceUtils;
import ru.itis.maletskov.util.validator.UserFormValidator;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserFormValidator userFormValidator;

    @InitBinder("userForm")
    public void initUserFormBinder(WebDataBinder binder) {
        binder.addValidators(userFormValidator);
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
    public String registerUser(@ModelAttribute("user") User user,
                               @RequestParam(value = "singer", required = false) Boolean isSinger,
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
            Map<String, String> errorsMap = ServiceUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            return "registration";
        }
        if (isSinger == null) {
            isSinger = false;
        }
        if (!userService.addUser(user, isSinger)) {
            model.addAttribute("usernameError", "User with that email or username already exists");
            model.addAttribute("emailError", "User with that email or username already exists");
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
        model.addAttribute("isCurrentUser", pathUser == null || user.getId().equals(pathUser.getId()));
        model.addAttribute("user", user);
        model.addAttribute("userChannel", pathUser);
        model.addAttribute("isSubscriber", pathUser != null && pathUser.getSubscribers().contains(user));
        return "user_profile";
    }

    @GetMapping("/edit_profile")
    public String editProfilePage(@AuthenticationPrincipal User user,
                                  Model model) {
        model.addAttribute("userForm", UserForm.fromUserToForm(userService.findById(user.getId())));
        return "edit_profile";
    }

    @PostMapping("/edit_profile")
    public String editProfile(@AuthenticationPrincipal User user,
                              @Validated @ModelAttribute("userForm") UserForm form,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            return "edit_profile";
        }
        userService.updateInfo(user, form);
        return "redirect:/user_profile/" + user.getId();
    }

    @GetMapping("/user_songs/{user}")
    public String userSongsPage() {
        return "user_songs";
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
            if (!user.getSubscriptions().isEmpty()) {
                model.addAttribute("users", user.getSubscriptions());
            }
        } else {
            if (!user.getSubscribers().isEmpty()) {
                model.addAttribute("users", user.getSubscribers());
            }
        }
        model.addAttribute("title", "subscriptions".equals(type) ? "Subscriptions" : "Subscribers");
        return "subscriptions";
    }

    @GetMapping("/user/search")
    public String userSearchPage() {
        return "user_search";
    }

    @GetMapping("/user/{user}/add_song")
    public String addSongToFavourite(@RequestParam("song") Song song,
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