package com.mercadolibre.ingrid_bicudo.repository;

import java.util.UUID;

import com.mercadolibre.ingrid_bicudo.model.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    @Query("FROM Account a WHERE a.userName = :userName")
    Account findAccount(@Param("userName") String userName);
}