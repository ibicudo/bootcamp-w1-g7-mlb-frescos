package com.mercadolibre.ingrid_bicudo.service.account;

import com.mercadolibre.ingrid_bicudo.model.Account;

import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    Account getAccountByName(String userName);
}
