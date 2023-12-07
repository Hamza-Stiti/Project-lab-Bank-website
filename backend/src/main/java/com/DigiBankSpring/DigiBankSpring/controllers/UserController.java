package com.DigiBankSpring.DigiBankSpring.controllers;


import com.DigiBankSpring.DigiBankSpring.models.Recipient;
import com.DigiBankSpring.DigiBankSpring.requests.LoginRequest;
import com.DigiBankSpring.DigiBankSpring.requests.SendRequest;
import com.DigiBankSpring.DigiBankSpring.responses.CurrencyResponce;
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


    @GetMapping("/get")
    public ResponseEntity<Long> User(@RequestHeader (name="Authorization") String authHeader) {
        long userId = jwtDecoder.getUserIdFromAuthHeader(authHeader);
        return ResponseEntity.ok(userId);
    }


    @GetMapping("/currency")
    public ResponseEntity<CurrencyResponce> add(@RequestHeader (name="Authorization") String authHeader){
        long userID = jwtDecoder.getUserIdFromAuthHeader(authHeader);
        Optional<CurrencyResponce> result = userService.getAccountByUserID(userID);
        if(result.isEmpty())
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok(result.get());
    }

//    @GetMapping("")
//    public ResponseEntity<List<Recipient>> getRecipients(@RequestHeader (name="Authorization") String authHeader){
//
//    }

    @GetMapping("/send")
    public ResponseEntity<String> SendMoney(@RequestHeader (name="Authorization") String authHeader, @RequestBody SendRequest details){
        long userId = jwtDecoder.getUserIdFromAuthHeader(authHeader);
        Optional<String> response = userService.sendMoney(details, userId);
        if(!response.isEmpty()) return ResponseEntity.badRequest().body(response.get());
        return ResponseEntity.ok("money sent");
    }
}
