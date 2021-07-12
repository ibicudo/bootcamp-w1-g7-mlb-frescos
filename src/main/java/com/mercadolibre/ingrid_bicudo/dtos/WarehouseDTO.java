package com.mercadolibre.ingrid_bicudo.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseDTO {

    @NotNull
    private String code;
    @NotNull
    private String name;
}
