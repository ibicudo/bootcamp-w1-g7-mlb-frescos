package com.mercadolibre.bootcamp_it_test_lucas.service;

import com.mercadolibre.bootcamp_it_test_lucas.dtos.LoginResponseDTO;

import javassist.NotFoundException;

public interface SessionService {
    LoginResponseDTO login(String userName, String password) throws NotFoundException;
}