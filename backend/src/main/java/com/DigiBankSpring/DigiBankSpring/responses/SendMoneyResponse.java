package com.DigiBankSpring.DigiBankSpring.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SendMoneyResponse {
    private boolean success;
    private String error;
}
