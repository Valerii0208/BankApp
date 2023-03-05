package com.example.bankdemo.web;

import com.example.bankdemo.entity.Card;
import com.example.bankdemo.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;
    @RequestMapping(method = RequestMethod.GET, value = "/getCard")
    public String getCard (Card card, Model model) {
        model.addAttribute("card", card);
        cardService.createNewCard(card);
        return "personal-page";
    }
}
