package com.example.bankdemo.service;

import com.example.bankdemo.entity.Card;
import com.example.bankdemo.entity.User;

import java.util.List;

public interface CardService {
    Card createNewCard(User user);

    List<Card> getAllCardsForCurrentUSer();
}
