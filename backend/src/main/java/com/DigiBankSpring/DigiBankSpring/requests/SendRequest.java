package com.DigiBankSpring.DigiBankSpring.requests;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SendRequest {
    long amount;
    long IBAN;
}
