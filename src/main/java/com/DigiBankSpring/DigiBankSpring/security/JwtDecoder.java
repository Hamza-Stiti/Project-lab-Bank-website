package com.DigiBankSpring.DigiBankSpring.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtDecoder
{
    private final JwtProperties properties;

    public DecodedJWT decodedToken(String token)
    {
        return JWT.require(Algorithm.HMAC256(properties.getKey())).build().verify(token);
    }
}
