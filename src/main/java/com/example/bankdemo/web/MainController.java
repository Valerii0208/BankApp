package com.example.bankdemo.web;

import com.example.bankdemo.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/bank")
@Controller
public class MainController {
    @GetMapping("/main-page")
    public String mainPage() {
        return "main-page";
    }

    @GetMapping("/sign-up")
    public String signUpForm(Model model) {
        model.addAttribute("user", new User());
        return "sign-up-form";
    }

    @GetMapping("/sign-in")
    public String signInForm() {
        return "sign-in-form";
    }
}
