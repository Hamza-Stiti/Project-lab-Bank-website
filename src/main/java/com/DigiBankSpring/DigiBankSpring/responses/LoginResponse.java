package com.DigiBankSpring.DigiBankSpring.responses;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse
{
    private String accessToken;
    private String error;
}
