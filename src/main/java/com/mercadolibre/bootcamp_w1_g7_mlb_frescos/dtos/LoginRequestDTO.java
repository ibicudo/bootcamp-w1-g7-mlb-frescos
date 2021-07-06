package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos;

import javax.validation.Valid;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Valid
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class LoginRequestDTO {
    private String userName;
    private String password;
}