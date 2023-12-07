package com.DigiBankSpring.DigiBankSpring.controllers;

import com.DigiBankSpring.DigiBankSpring.requests.LoginRequest;
import com.DigiBankSpring.DigiBankSpring.requests.RegisterRequest;
import com.DigiBankSpring.DigiBankSpring.responses.LoginResponse;
import com.DigiBankSpring.DigiBankSpring.security.JwtIssuer;
import com.DigiBankSpring.DigiBankSpring.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController
{
    private final JwtIssuer jwtIssuer;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request)
    {
        Optional<Long> id = userService.findUserByEmailAndPassword(request.getEmail(), request.getPassword());
        if (id.isEmpty()) return ResponseEntity.badRequest().body(LoginResponse.builder().error("Invalid credentials").build());

        String token = jwtIssuer.issue(id.get(), request.getEmail());
        return ResponseEntity.ok(LoginResponse.builder().accessToken(token).build());
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@RequestBody RegisterRequest request)
    {
        Optional<Long> id = userService.createNewUser(request.getName(), request.getEmail(), request.getPassword(), request. getDOB());
        if (id.isEmpty()) return ResponseEntity.badRequest().body(LoginResponse.builder().error("Email already exists").build());

        String token = jwtIssuer.issue(id.get(), request.getEmail());
        return ResponseEntity.ok(LoginResponse.builder().accessToken(token).build());
    }

    @GetMapping("/test")
    public String test() {
        return "authenticated";
    }
}
