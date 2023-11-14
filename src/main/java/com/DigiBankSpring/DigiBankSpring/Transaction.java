package com.DigiBankSpring.DigiBankSpring;

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

    @Column(name = "SENDER_IBAN") //foreign key from CURRENCY
    private long Sender_IBAN;

    @Column(name = "RECIPIENT_IBAN") //foreign key from CURRENCY
    private long Recipient_IBAN;

    @Column(name = "AMOUNT")
    private float amount;
}
