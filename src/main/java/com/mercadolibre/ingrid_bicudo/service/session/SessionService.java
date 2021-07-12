package com.mercadolibre.ingrid_bicudo.service.session;

import com.mercadolibre.ingrid_bicudo.dtos.LoginRequestDTO;
import com.mercadolibre.ingrid_bicudo.dtos.LoginResponseDTO;
import com.mercadolibre.ingrid_bicudo.exceptions.LoginFailedException;

public interface SessionService {
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO) throws LoginFailedException;
}