package com.DigiBankSpring.DigiBankSpring.services;

import com.DigiBankSpring.DigiBankSpring.models.Currency;
import com.DigiBankSpring.DigiBankSpring.models.Recipient;
import com.DigiBankSpring.DigiBankSpring.models.Transaction;
import com.DigiBankSpring.DigiBankSpring.models.User;
import com.DigiBankSpring.DigiBankSpring.repositories.CurrencyRepository;
import com.DigiBankSpring.DigiBankSpring.repositories.RecipientRepository;
import com.DigiBankSpring.DigiBankSpring.repositories.TransactionRepository;
import com.DigiBankSpring.DigiBankSpring.repositories.UserRepository;
import com.DigiBankSpring.DigiBankSpring.requests.SendRequest;
import com.DigiBankSpring.DigiBankSpring.responses.CurrencyResponce;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService
{
    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;
    private final TransactionRepository transactionRepository;
    private final RecipientRepository recipientRepository;

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
        Currency account = new Currency(user.getID());
        currencyRepository.save(account);
        return Optional.of(user.getID());
    }

    public Optional<CurrencyResponce> getAccountByUserID(long userID) {
        Optional<Currency> QueryResult = currencyRepository.findByUserID(userID);
        if(QueryResult.isEmpty()) return Optional.empty();

        return Optional.of(QueryResult.get().convertToResponse());
    }


    @Transactional
    public Optional<String> sendMoney(SendRequest details, long userID){
        long amount = details.getAmount();
        long IBAN = details.getIBAN();
        if(currencyRepository.findByIBAN(details.getIBAN()).isEmpty())
            return Optional.of("Invalid IBAN");
        transactionRepository.save(new Transaction(userID, details.getAmount(), details.getIBAN()));
        currencyRepository.updateRecipientBalance(details.getAmount(), details.getIBAN());
        currencyRepository.updateSenderBalance(details.getAmount(), userID);
        if(recipientRepository.findByUserIDandRecipientIBAN(userID, details.getIBAN()).isEmpty())
            recipientRepository.save(new Recipient(userID, details.getIBAN()));

        return Optional.empty();
    }
}
