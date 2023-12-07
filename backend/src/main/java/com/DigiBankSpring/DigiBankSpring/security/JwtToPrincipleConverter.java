package com.DigiBankSpring.DigiBankSpring.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtToPrincipleConverter
{
    public UserPrinciple convert(DecodedJWT jwt)
    {
        return UserPrinciple.builder()
                .userId(Long.parseLong(jwt.getSubject()))
                .email(jwt.getClaim("email").asString())
                .authorities(List.of())
                .build();
    }
}
