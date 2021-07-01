package com.mercadolibre.bootcamp_it_test_lucas.controller;

import javax.validation.Valid;

import com.mercadolibre.bootcamp_it_test_lucas.dtos.LoginResponseDTO;
import com.mercadolibre.bootcamp_it_test_lucas.service.SessionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class SessionController {
    @Autowired
    private  SessionService service ;

    @PostMapping("/login")
    ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid String userName, @RequestBody @Valid String password){
        return  ResponseEntity.status(200).body(service.login(userName, password));
    }
}
