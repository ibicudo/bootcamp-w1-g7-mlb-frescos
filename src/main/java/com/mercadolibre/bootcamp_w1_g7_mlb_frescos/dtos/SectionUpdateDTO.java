package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.InboundOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SectionUpdateDTO {

    @NotNull
    private String code;

    @NotNull
    private String category;

    @NotNull
    private int capacity;

    @NotNull
    private WarehouseDTO warehouse;

}
