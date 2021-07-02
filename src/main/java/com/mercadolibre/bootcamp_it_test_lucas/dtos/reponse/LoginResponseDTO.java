package com.mercadolibre.bootcamp_it_test_lucas.dtos.reponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class LoginResponseDTO {
    private String username;
    private String token;
}
