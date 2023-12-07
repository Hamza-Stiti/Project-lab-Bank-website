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

    @OneToOne
    @JoinColumn(name = "sender")
    private User sender;

    @Column(name = "recipient")
    String recipientIBAN;
}
