package ru.itis.maletskov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/")
    public String root() {
        return "home";
    }
}
