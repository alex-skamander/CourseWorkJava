package com.coursework.Kotik.Controllers;

import com.coursework.Kotik.Models.Product;
import com.coursework.Kotik.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CatsController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/{type}")
    public String cats(Model model, @PathVariable("type") String type) {

        model.addAttribute("products", productRepository.findByType(type));
        System.out.println(productRepository.findAll());
        model.addAttribute("type", "Всё для " + type);
        return "cats";
    }

}
