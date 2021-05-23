package com.coursework.Kotik.Controllers;

import com.coursework.Kotik.Models.Purchase;
import com.coursework.Kotik.Models.User;
import com.coursework.Kotik.Service.CustomUserDetailsService;
import com.coursework.Kotik.Service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CartController {
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/cart")
    public String home(Authentication authentication, Model model) {
        String name = authentication.getName();
        User user = userDetailsService.findByUsername(name);
        model.addAttribute("product");
        return "cart";
    }

    @RequestMapping(value = "/cart/addProduct/{id}", method = RequestMethod.POST)
    @ResponseBody
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
