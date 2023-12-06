package com.DigiBankSpring.DigiBankSpring.controllers;

import com.DigiBankSpring.DigiBankSpring.repositories.CurrencyRepository;
import com.DigiBankSpring.DigiBankSpring.responses.LoginResponse;
import com.DigiBankSpring.DigiBankSpring.security.JwtDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Optional;

import static java.lang.Long.parseLong;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController
{
    private final JwtDecoder jwtDecoder;
    private final CurrencyRepository currencyRepository;

    @GetMapping("/get")
    public ResponseEntity<Long> User(@RequestHeader (name="Authorization") String authHeader) {
        Long userId = parseLong(jwtDecoder.getUserIdFromAuthHeader(authHeader));
        return ResponseEntity.ok(userId);
    }

//    @GetMapping("/currencylist")
//    public ResponseEntity<ArrayList<String>> GetCurrecies(@RequestHeader (name="Authorization") String authHeader){
//        Long userId = parseLong(jwtDecoder.getUserIdFromAuthHeader(authHeader));
//        Optional<ArrayList<Currency>> currency = currencyRepository.findByUserID(userId);
//        if (currency.isEmpty()) return ResponseEntity.badRequest().build();
//        ArrayList<Currency> result = new ArrayList<>(currency.get());
//        return ResponseEntity.ok(result);
//    }
}
