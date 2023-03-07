package com.example.bankdemo.web;

import com.example.bankdemo.entity.User;
import com.example.bankdemo.service.CardService;
import com.example.bankdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class CardController {
    private final UserService userService;
    private final CardService cardService;

    @PostMapping("/addCard")
    public String showProfilePage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findByLogin(username);
        user.addCard(cardService.createNewCard(user));

        return "redirect:/api/users/personal-page";
    }

    @GetMapping("/showAllCards")
    public String showAllCards() {
        cardService.getAllCardsForCurrentUSer();
        return "redirect:/api/users/personal-page";
    }
}

