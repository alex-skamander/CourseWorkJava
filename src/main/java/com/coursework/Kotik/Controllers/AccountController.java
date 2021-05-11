package com.coursework.Kotik.Controllers;

import com.coursework.Kotik.Models.Account;
import com.coursework.Kotik.Repositories.AccountRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {
    @GetMapping("/account")
    public String sign(Model model) {
        return "account";
    }

    @PostMapping("/account")
    public String signAccount (@RequestParam String username, @RequestParam String password ) {
        Account account = new Account(username, password);
        /*AccountRepository.save(account);*/
        return "redirect:/";

    }
}
