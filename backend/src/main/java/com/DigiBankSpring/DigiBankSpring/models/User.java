package com.DigiBankSpring.DigiBankSpring.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "USER")
@Data
@NoArgsConstructor
public class User
{
    public User(String name, String email, String password, Date DOB)
    {
        this.name = name;
        this.email = email;
        this.password = password;
        this.DOB = DOB;
    }

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