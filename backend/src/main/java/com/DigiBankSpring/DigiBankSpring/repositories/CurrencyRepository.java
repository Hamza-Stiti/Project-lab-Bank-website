package com.DigiBankSpring.DigiBankSpring.repositories;


import com.DigiBankSpring.DigiBankSpring.models.Currency;
import com.DigiBankSpring.DigiBankSpring.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
