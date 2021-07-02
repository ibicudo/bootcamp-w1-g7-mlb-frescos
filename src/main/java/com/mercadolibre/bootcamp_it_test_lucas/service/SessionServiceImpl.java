package com.mercadolibre.bootcamp_it_test_lucas.service;

import com.mercadolibre.bootcamp_it_test_lucas.dtos.reponse.LoginResponseDTO;
import com.mercadolibre.bootcamp_it_test_lucas.dtos.request.LoginRequestDTO;
import com.mercadolibre.bootcamp_it_test_lucas.model.Account;
import com.mercadolibre.bootcamp_it_test_lucas.repository.AccountRepository;
import com.mercadolibre.bootcamp_it_test_lucas.security.JWTUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) throws NotFoundException {
        Account account =  accountRepository.findAccount(loginRequestDTO.getUserName(), loginRequestDTO.getPassword());
        
        if (account == null){
            throw new NotFoundException("Account User or Password is wrong");
        }    

        return new LoginResponseDTO(account.getUserName(), JWTUtil.getJWT(account));
    }
}
