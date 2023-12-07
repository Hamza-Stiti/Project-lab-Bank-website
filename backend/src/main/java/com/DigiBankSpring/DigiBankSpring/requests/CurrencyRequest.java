package com.DigiBankSpring.DigiBankSpring.requests;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CurrencyRequest {
    private String name;
    private long User_ID;
    private float balance;
}
