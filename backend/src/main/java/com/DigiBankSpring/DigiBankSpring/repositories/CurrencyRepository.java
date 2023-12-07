package com.DigiBankSpring.DigiBankSpring.repositories;


import com.DigiBankSpring.DigiBankSpring.models.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    @Query(value = "select * FROM CURRENCY WHERE CURRENCY.USER_ID = :USER_ID", nativeQuery = true)
    Optional<Currency> findByUserID(@Param("USER_ID") Long userID);

    @Query(value = "select * FROM CURRENCY WHERE CURRENCY.IBAN = :IBAN", nativeQuery = true)
    public List<Currency> findByIBAN(@Param("IBAN") Long IBAN);

    @Modifying
    @Query(value = "update Currency Set BALANCE = BALANCE - :amount Where Currency.USER_ID = :USER_ID", nativeQuery = true)
    public void updateSenderBalance(@Param("amount") float amount, @Param("USER_ID") long userID);

    @Modifying
    @Query(value = "update Currency Set BALANCE = :amount + BALANCE Where Currency.IBAN = :IBAN", nativeQuery = true)
    public void updateRecipientBalance(@Param("amount") float amount, @Param("IBAN") long IBAN);

}
