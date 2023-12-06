package com.DigiBankSpring.DigiBankSpring.repositories;

import com.DigiBankSpring.DigiBankSpring.models.Currency;
import com.DigiBankSpring.DigiBankSpring.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Optional;

public interface CurrencyRepository {
    @Query("SELECT u FROM Currency u WHERE u.UserID = :ID")
    Optional<ArrayList<Currency>> findByUserID(@Param("ID") Long ID);
}
