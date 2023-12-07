package com.DigiBankSpring.DigiBankSpring.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "TRANSACTION")
@Data
public class Transaction {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long Transfer_Number;

//    @Column(name = "SENDER_IBAN") //foreign key from CURRENCY
    @ManyToOne
    @JoinColumn(name = "SENDER_IBAN")
    private Currency Sender_IBAN;

//    @Column(name = "RECIPIENT_IBAN") //foreign key from CURRENCY
    @ManyToOne
    @JoinColumn(name = "RECIPIENT_IBAN")
    private Currency Recipient_IBAN;

    @Column(name = "AMOUNT")
    private float amount;
}
