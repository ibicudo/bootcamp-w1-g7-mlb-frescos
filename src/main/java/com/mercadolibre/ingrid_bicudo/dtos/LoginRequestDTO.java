package com.mercadolibre.ingrid_bicudo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class LoginRequestDTO {

    @NotNull(message = "The userName is required")
    private String userName;

    @NotNull(message = "The password is required")
    private String password;
}