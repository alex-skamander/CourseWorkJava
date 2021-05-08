package com.coursework.Kotik.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DogsController {

    @GetMapping("/dogs")
    public String dogs(Model model) {
        return "dogs";
    }

}
