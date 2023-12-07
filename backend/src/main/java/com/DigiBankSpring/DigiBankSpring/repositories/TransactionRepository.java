package com.DigiBankSpring.DigiBankSpring.repositories;

import com.DigiBankSpring.DigiBankSpring.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(value = "select * FROM TRANSACTION WHERE TRANSACTION.SENDER_ID = :USER_ID", nativeQuery = true)
    List<Transaction> findBySenderID(@Param("USER_ID") Long UserID);
}
