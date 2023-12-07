package com.DigiBankSpring.DigiBankSpring.repositories;

import com.DigiBankSpring.DigiBankSpring.models.Currency;
import com.DigiBankSpring.DigiBankSpring.models.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipientRepository extends JpaRepository<Recipient, Long> {
//    @Query(value = "select * FROM Recipient WHERE Recipient.sender = :USER_ID", nativeQuery = true)
//    List<Recipient> findByUserID(@Param("USER_ID") long UserID);

    @Query(value = "select * FROM Recipient WHERE Recipient.sender_ID = :USER_ID and Recipient.recipient_IBAN = :IBAN", nativeQuery = true)
    Optional<Recipient> findByUserIDandRecipientIBAN(@Param("USER_ID") long UserID, @Param("IBAN") long IBAN);
}
