package com.coursework.Kotik.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignController {
    @GetMapping("/sign")
    public String sign(Model model) {
        return "sign";
    }
}
