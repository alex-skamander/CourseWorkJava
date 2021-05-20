package com.coursework.Kotik.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "KOTIK");
        return "home";
    }

    @GetMapping("/home")
    public String homes(Model model) {
        model.addAttribute("title", "KOTIK");
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

}
