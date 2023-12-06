package com.DigiBankSpring.DigiBankSpring.services;

import com.DigiBankSpring.DigiBankSpring.repositories.CurrencyRepository;
import com.DigiBankSpring.DigiBankSpring.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;
}
