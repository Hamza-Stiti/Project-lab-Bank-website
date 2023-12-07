package com.DigiBankSpring.DigiBankSpring.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "TRANSACTION")
@Data
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long Transfer_Number;

    @Column(name = "SENDER_ID") //foreign key from CURRENCY
    private long sender;

    @Column(name = "RECIPIENT_IBAN") //foreign key from CURRENCY
    private long recipientIBAN;

    @Column(name = "AMOUNT")
    private float amount;

    @Column(name = "Date")
    private LocalDate date;

    public Transaction(long senderID, float amount, long recipientIban){
        sender = senderID;
        this.amount = amount;
        this.recipientIBAN = recipientIban;
        date = java.time.LocalDate.now();
    }


}
