package com.DigiBankSpring.DigiBankSpring.services;

import com.DigiBankSpring.DigiBankSpring.models.User;
import com.DigiBankSpring.DigiBankSpring.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<Long> findUserByEmailAndPassword(String email, String password)
    {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) return Optional.empty();
        if (!passwordEncoder.matches(password, user.get().getPassword())) return Optional.empty();
        return Optional.of(user.get().getID());
    }

    public Optional<Long> createNewUser(String name, String email, String password, String DOB)
    {
        if (userRepository.findByEmail(email).isPresent()) return Optional.empty();

        User user = new User(name, email, passwordEncoder.encode(password), new Date(DOB));
        userRepository.save(user);
        return Optional.of(user.getID());
    }
}
