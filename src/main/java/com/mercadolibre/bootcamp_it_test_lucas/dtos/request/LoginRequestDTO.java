package com.mercadolibre.bootcamp_it_test_lucas.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class LoginRequestDTO {
    private String userName;
    private String password;
}
