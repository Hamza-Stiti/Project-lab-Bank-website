package com.DigiBankSpring.DigiBankSpring;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "USER")
@Data
public class User {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long ID;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "DOB")
    private Date DOB;


    @OneToMany
    @JoinColumn(name = "USER_ID")
    private List<Currency> currency;

}