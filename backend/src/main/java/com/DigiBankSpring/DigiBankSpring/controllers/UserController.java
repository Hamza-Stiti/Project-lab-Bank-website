package com.DigiBankSpring.DigiBankSpring.controllers;


import com.DigiBankSpring.DigiBankSpring.models.Recipient;
import com.DigiBankSpring.DigiBankSpring.models.Transaction;
import com.DigiBankSpring.DigiBankSpring.requests.LoginRequest;
import com.DigiBankSpring.DigiBankSpring.requests.SendRequest;
import com.DigiBankSpring.DigiBankSpring.responses.CurrencyResponce;
import com.DigiBankSpring.DigiBankSpring.responses.SendMoneyResponse;
import com.DigiBankSpring.DigiBankSpring.security.JwtDecoder;
import com.DigiBankSpring.DigiBankSpring.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

import static java.lang.Long.parseLong;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController
{
    private final JwtDecoder jwtDecoder;
    private final UserService userService;


    @GetMapping("/currency")
    public ResponseEntity<CurrencyResponce> add(@RequestHeader (name="Authorization") String authHeader){
        long userID = jwtDecoder.getUserIdFromAuthHeader(authHeader);
        Optional<CurrencyResponce> result = userService.getAccountByUserID(userID);
        if(result.isEmpty())
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok(result.get());
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getTransactions(@RequestHeader (name="Authorization") String authHeader){
        long userID = jwtDecoder.getUserIdFromAuthHeader(authHeader);
        Optional<List<Transaction>> response = userService.getTransactions(userID);
        if(response.isEmpty()) return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok(response.get());
    }

    @GetMapping("/recipients")
    public ResponseEntity<List<Recipient>> getRecipients(@RequestHeader (name="Authorization") String authHeader){
        long userID = jwtDecoder.getUserIdFromAuthHeader(authHeader);
        Optional<List<Recipient>> response = userService.getRecipients(userID);
        if(response.isEmpty()) return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok(response.get());
    }

    @PostMapping("/send")
    public ResponseEntity<SendMoneyResponse> SendMoney(@RequestHeader (name="Authorization") String authHeader, @RequestBody SendRequest details){
        long userId = jwtDecoder.getUserIdFromAuthHeader(authHeader);
        Optional<String> response = userService.sendMoney(details, userId);
        if(!response.isEmpty()) return ResponseEntity.badRequest().body(SendMoneyResponse.builder().success(false).error(response.get()).build());
        return ResponseEntity.ok(SendMoneyResponse.builder().success(true).build());
    }
}
