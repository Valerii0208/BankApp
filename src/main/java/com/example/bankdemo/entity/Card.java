package com.example.bankdemo.entity;

import com.example.bankdemo.entity.enm.CardType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue
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
    @Column(name = "card_status")
    private boolean active;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
