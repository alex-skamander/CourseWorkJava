package com.coursework.Kotik.Controllers;

import com.coursework.Kotik.Service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductController {
    @Autowired
    private MainService mainService;

    @GetMapping("/{type}")
    public String cats(Model model, @PathVariable("type") String type) {
        model.addAttribute("products", mainService.findByType(type));
        model.addAttribute("type", "Everything for " + type);
        return "product";
    }

}
