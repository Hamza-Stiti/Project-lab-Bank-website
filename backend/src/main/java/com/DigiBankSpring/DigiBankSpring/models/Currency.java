package com.DigiBankSpring.DigiBankSpring.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "CURRENCY")
@Data
public class Currency {

    @Id
    @GeneratedValue
    @Column(name = "IBAN")
    private long IBAN;

    @Column(name = "NAME")
    private final String name = "HUF";

    @Column(name = "USER_ID") //foreign key from USER
    private long User_ID;

    @Column(name = "BALANCE")
    private float balance;

    public Currency(long user_ID){
        this.User_ID = user_ID;
        balance = 0;
    }
}
