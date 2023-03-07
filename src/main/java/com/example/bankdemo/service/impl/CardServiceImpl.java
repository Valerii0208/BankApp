package com.example.bankdemo.service.impl;

import com.example.bankdemo.entity.Card;
import com.example.bankdemo.entity.User;
import com.example.bankdemo.entity.enm.CardType;
import com.example.bankdemo.entity.enm.PaymentSystem;
import com.example.bankdemo.repository.CardRepository;
import com.example.bankdemo.service.CardService;
import com.example.bankdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;

    private final UserService userService;
    @Override
    public Card createNewCard(User user) {
        Random random = new Random();
        Card card = new Card();



        PaymentSystem[] paymentSystems = PaymentSystem.values();
        int randomIndexForPaymentSystem = random.nextInt(paymentSystems.length);
        PaymentSystem randomPaymentSystem = paymentSystems[randomIndexForPaymentSystem];
        card.setPaymentSystem(randomPaymentSystem);

        card.setNumber(generateBankCardNumber(randomPaymentSystem.toString()));

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

        CardType[] cardType = CardType.values();
        int randomIndexForCardType = random.nextInt(cardType.length);
        CardType randomCardType = cardType[randomIndexForCardType];


        card.setCardType(randomCardType);
        card.setActive(true);

        card.setUser(user);
        cardRepository.save(card);

        return card;
    }

    public static String generateBankCardNumber(String cardType) {
        Random rand = new Random();
        String bankNumber = "";

        if (cardType.equals("VISA")) {
            bankNumber += "4";
            for (int i = 1; i < 16; i++) {
                bankNumber += rand.nextInt(10);
            }
        } else if (cardType.equals("Mastercard")) {
            bankNumber += "5";
            bankNumber += (rand.nextInt(5) + 1);
            for (int i = 2; i < 16; i++) {
                bankNumber += rand.nextInt(10);
            }
        } else {
            return "Invalid card type";
        }

        int[] digits = new int[bankNumber.length()];
        for (int i = 0; i < bankNumber.length(); i++) {
            digits[i] = Character.getNumericValue(bankNumber.charAt(i));
        }
        for (int i = digits.length - 2; i >= 0; i -= 2) {
            int doubleDigit = digits[i] * 2;
            if (doubleDigit > 9) {
                doubleDigit -= 9;
            }
            digits[i] = doubleDigit;
        }
        int sum = 0;
        for (int digit : digits) {
            sum += digit;
        }
        int checkDigit = (10 - (sum % 10)) % 10;

        // Return the complete bank card number
        return bankNumber + checkDigit;
    }

    @Override
    public List<Card> getAllCardsForCurrentUSer() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findByLogin(username);

        List<Card> cards = user.getCards();

        return cards;
    }
}
