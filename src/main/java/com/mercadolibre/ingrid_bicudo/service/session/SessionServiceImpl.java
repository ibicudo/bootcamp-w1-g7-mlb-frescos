package com.mercadolibre.ingrid_bicudo.service.session;

import com.mercadolibre.ingrid_bicudo.dtos.LoginRequestDTO;
import com.mercadolibre.ingrid_bicudo.dtos.LoginResponseDTO;
import com.mercadolibre.ingrid_bicudo.exceptions.LoginFailedException;
import com.mercadolibre.ingrid_bicudo.exceptions.NotFoundException;
import com.mercadolibre.ingrid_bicudo.model.Account;
import com.mercadolibre.ingrid_bicudo.repository.AccountRepository;
import com.mercadolibre.ingrid_bicudo.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) throws NotFoundException {
        Account account =  accountRepository.findAccount(loginRequestDTO.getUserName());

        if (account == null ||
                ! BCrypt.checkpw(loginRequestDTO.getPassword(), account.getPassword()))
        {
            throw new LoginFailedException("Account User or Password is wrong");
        }
        return new LoginResponseDTO(account.getId(), JWTUtil.getJWT(account));
    }
}