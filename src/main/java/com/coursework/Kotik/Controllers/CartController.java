package com.coursework.Kotik.Controllers;

import com.coursework.Kotik.Models.Purchase;
import com.coursework.Kotik.Models.User;
import com.coursework.Kotik.Service.CustomUserDetailsService;
import com.coursework.Kotik.Service.ProductService;
import com.coursework.Kotik.Service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private ProductService productService;

    @GetMapping("/cart")
    public String home(Authentication authentication, Model model) {
        User user = ((User) userDetailsService.findByUsername(authentication.getName()));
        int sum = 0;
        List<Purchase> purchaseList = purchaseService.getAllPurchaseUser(user.getId());
        for (Purchase purchase: purchaseList){
            sum += productService.findProductById(purchase.getProductId()).getPrice() * purchase.getProduct_count();
        }
        model.addAttribute("total", sum);
        model.addAttribute("purchaseList", purchaseList);
        model.addAttribute("productService", productService);
        return "cart";
    }

    @ResponseBody
    @PostMapping("/cart/addPurchase/{id}")
    public void addProduct(Authentication authentication, @PathVariable("id") Long productId, Model model){
        User user = userDetailsService.findByUsername(authentication.getName());
        System.out.println(user.getUsername());

        if(authentication.isAuthenticated()){
            Purchase purchase = purchaseService.getPurchaseByUserIdAndProductId(user.getId(), productId);
            if (purchase == null){
                Purchase newPurchase = new Purchase();
                newPurchase.setUserId(user.getId());
                newPurchase.setProductId(productId);
                newPurchase.setProduct_count(1);
                purchaseService.addPurchase(newPurchase);
            } else {
                purchase.setProduct_count(purchase.getProduct_count() + 1);
                purchaseService.addPurchase(purchase);
            }
        }
    }
}