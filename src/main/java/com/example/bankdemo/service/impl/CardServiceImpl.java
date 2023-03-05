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
        card.setNumber(generateBankCardNumber("VISA"));

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

    // Card number generation method based on Luhn algorithm
    public static String generateBankCardNumber(String cardType) {
        Random rand = new Random();
        String bankNumber = "";

        // Generate a random number based on the card type
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
        } else if (cardType.equals("American Express")) {
            bankNumber += "3";
            bankNumber += (rand.nextInt(4) + 4);
            bankNumber += (rand.nextInt(10));
            for (int i = 3; i < 15; i++) {
                bankNumber += rand.nextInt(10);
            }
        } else {
            return "Invalid card type";
        }

        // Calculate the check digit using the Luhn algorithm
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
}
