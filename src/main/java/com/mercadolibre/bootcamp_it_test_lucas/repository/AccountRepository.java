package com.mercadolibre.bootcamp_it_test_lucas.repository;

import java.util.UUID;

import com.mercadolibre.bootcamp_it_test_lucas.model.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    @Query("FROM Account a WHERE a.userName = :userName and a.password = :password")
    Account findAccount(@Param("userName") String userName, @Param("password") String password);
}
