package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.controller;

import javax.validation.Valid;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.LoginRequestDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.LoginResponseDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.service.session.SessionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class SessionController {
    @Autowired
    private  SessionService service;

    @PostMapping("/login")
    ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequest ){
        return  ResponseEntity.status(200).body(service.login(loginRequest));
    }
}