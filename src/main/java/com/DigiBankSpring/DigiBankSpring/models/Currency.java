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
    private String name;

    @Column(name = "USER_ID") //foreign key from USER
    private long User_ID;

    @Column(name = "BALANCE")
    private float balance;
}
