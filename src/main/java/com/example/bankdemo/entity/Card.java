package com.example.bankdemo.entity;

import com.example.bankdemo.entity.enm.CardType;
import com.example.bankdemo.entity.enm.PaymentSystem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "card_id")
    private long id;
    @Column(name = "card_number")
    private String number;
    @Column(name = "card_expirationDate")
    private String expirationDate;
    @Column(name = "card_cvv")
    private int cvv;
    @Column(name = "card_type")
    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @Column(name = "card_paymentSystem")
    @Enumerated(EnumType.STRING)
    private PaymentSystem paymentSystem;
    @Column(name = "card_status")
    private boolean active;

    @Column(name = "card_balance")
    private int cardBalance;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;
}
