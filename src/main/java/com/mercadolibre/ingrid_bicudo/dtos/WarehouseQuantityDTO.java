package com.mercadolibre.ingrid_bicudo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class WarehouseQuantityDTO {

    @NotNull(message = "The warehouseCode is required")
    private String warehouseCode;
    @NotNull
    private String totalQuantity;
}
