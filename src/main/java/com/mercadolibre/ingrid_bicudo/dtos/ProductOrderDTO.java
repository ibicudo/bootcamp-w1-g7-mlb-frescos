package com.mercadolibre.ingrid_bicudo.dtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderDTO {
    private UUID productId;
    private int quantity;
}
