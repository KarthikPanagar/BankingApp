package com.karthikpanagar.bankingapp.repository;

import com.karthikpanagar.bankingapp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT MAX(a.accNumber) FROM Account a")    //JPQL
    Optional<Long> findMaxAccNumber();
}
