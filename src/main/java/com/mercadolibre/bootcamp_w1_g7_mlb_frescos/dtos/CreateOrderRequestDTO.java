package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequestDTO {
    private List<ProductOrderDTO> order;
}
