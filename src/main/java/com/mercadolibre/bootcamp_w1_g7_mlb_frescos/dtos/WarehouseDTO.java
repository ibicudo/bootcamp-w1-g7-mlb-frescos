package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos;


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
