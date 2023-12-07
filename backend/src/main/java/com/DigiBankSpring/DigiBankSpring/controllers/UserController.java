package com.DigiBankSpring.DigiBankSpring.controllers;


import com.DigiBankSpring.DigiBankSpring.requests.CurrencyRequest;
import com.DigiBankSpring.DigiBankSpring.security.JwtDecoder;
import com.DigiBankSpring.DigiBankSpring.services.CurrencyService;
import com.DigiBankSpring.DigiBankSpring.services.RecipientService;
import com.DigiBankSpring.DigiBankSpring.services.TransactionService;
import com.DigiBankSpring.DigiBankSpring.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import static java.lang.Long.parseLong;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController
{
    private final JwtDecoder jwtDecoder;
    private final UserService userService;
    private final CurrencyService currencyService;
    private final RecipientService recipientService;
    private final TransactionService transactionService;


    @GetMapping("/get")
    public ResponseEntity<Long> User(@RequestHeader (name="Authorization") String authHeader) {
        long userId = jwtDecoder.getUserIdFromAuthHeader(authHeader);
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

    @PostMapping("/add/currency")
    public ResponseEntity add(@RequestHeader (name="Authorization") String authHeader, @RequestBody CurrencyRequest request){
        long userID = jwtDecoder.getUserIdFromAuthHeader(authHeader);
        return (ResponseEntity) ResponseEntity.status(201);
    }

}
