package com.coursework.Kotik.Controllers;

import com.coursework.Kotik.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/{type}")
    public String cats(Model model, @PathVariable("type") String type) {

        model.addAttribute("products", productRepository.findByType(type));
        System.out.println(productRepository.findAll());
        model.addAttribute("type", "Everything for " + type);
        return "product";
    }

}
