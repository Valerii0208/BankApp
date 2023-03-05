package com.example.bankdemo.service.impl;

import com.example.bankdemo.entity.Card;
import com.example.bankdemo.entity.enm.CardType;
import com.example.bankdemo.repository.CardRepository;
import com.example.bankdemo.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    @Override
    public Card createNewCard(Card card) {
        Random random = new Random();
        final long MIN_BOUND_OF_CARD_NUMBER = 5375000000000L;
        final long MAX_BOUND_OF_CARD_NUMBER = 5375999999999L;
        card.setNumber(random.nextLong((MAX_BOUND_OF_CARD_NUMBER - MIN_BOUND_OF_CARD_NUMBER) + 1) + MIN_BOUND_OF_CARD_NUMBER);

        final int MIN_BOUND_OF_CARD_YEAR = 28;
        final int MAX_BOUND_OF_CARD_YEAR = 35;
        final int MIN_BOUND_OF_CARD_MONTH = 1;
        final int MAX_BOUND_OF_CARD_MONTH = 12;
        int year = random.nextInt((MAX_BOUND_OF_CARD_YEAR - MIN_BOUND_OF_CARD_YEAR) + 1) + MIN_BOUND_OF_CARD_YEAR;
        int month = random.nextInt((MAX_BOUND_OF_CARD_MONTH - MIN_BOUND_OF_CARD_MONTH) + 1) + MIN_BOUND_OF_CARD_MONTH;
        String cardExpirationDate = month + "/" + year;
        card.setExpirationDate(cardExpirationDate);

        final int MIN_BOUND_OF_CARD_CVV_CODE = 101;
        final int MAX_BOUND_OF_CARD_CVV_CODE = 999;
        card.setCvv(random.nextInt((MAX_BOUND_OF_CARD_CVV_CODE - MIN_BOUND_OF_CARD_CVV_CODE) + 1) + MIN_BOUND_OF_CARD_CVV_CODE);

        card.setCardType(CardType.CREDIT);
        card.setActive(true);

        return cardRepository.save(card);
    }
}
