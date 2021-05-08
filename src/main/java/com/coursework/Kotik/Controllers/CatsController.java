package com.coursework.Kotik.Controllers;

import com.coursework.Kotik.Models.Product;
import com.coursework.Kotik.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CatsController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/cats")
    public String cats(Model model) {
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "cats";
    }

}
