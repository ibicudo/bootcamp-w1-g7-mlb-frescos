package com.mercadolibre.bootcamp_it_test_lucas.service;

import com.mercadolibre.bootcamp_it_test_lucas.dtos.reponse.LoginResponseDTO;
import com.mercadolibre.bootcamp_it_test_lucas.dtos.request.LoginRequestDTO;

import javassist.NotFoundException;

public interface SessionService {
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO) throws NotFoundException;
}