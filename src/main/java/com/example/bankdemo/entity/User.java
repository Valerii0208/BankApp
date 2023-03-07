package com.example.bankdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long id;
    @Column(name = "user_firstname")
    private String firstname;
    @Column(name = "user_lastname")
    private String lastname;
    @Column(name = "user_age")
    private int age;
    @Column(name = "user_login", unique = true)
    private String login;
    @Column(name = "user_password")
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Card> cards = new ArrayList<>();
    public void addCard(Card card) {
        cards.add(card);
        card.setUser(this);
    }

    public void removeCard(Card card) {
        cards.remove(card);
        card.setUser(null);
    }

}
