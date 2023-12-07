package com.DigiBankSpring.DigiBankSpring.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class CurrencyResponce {
    private long IBAN;
    private float amount;

    public CurrencyResponce(long iban, float balance) {
        IBAN = iban;
        amount = balance;
    }
}
