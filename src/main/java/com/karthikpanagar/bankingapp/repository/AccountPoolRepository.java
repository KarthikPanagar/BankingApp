package com.karthikpanagar.bankingapp.repository;

import com.karthikpanagar.bankingapp.entity.AccountPool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountPoolRepository extends JpaRepository<AccountPool, Integer> {

    @Query(value = "SELECT * FROM account_pool ORDER BY id ASC LIMIT 1", nativeQuery = true) //SQL
    Optional<AccountPool> findFirstByOrderByIdAsc();
}
