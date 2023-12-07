package com.DigiBankSpring.DigiBankSpring.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "RECIPIENT")
@Data
@NoArgsConstructor
public class Recipient {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long ID;

    @Column(name = "sender_ID")
    private long sender;

    @Column(name = "recipient_IBAN")
    private long recipientIBAN;

    public Recipient(long sender, long recipientIBAN){
        this.sender = sender;
        this.recipientIBAN = recipientIBAN;
    }
}
