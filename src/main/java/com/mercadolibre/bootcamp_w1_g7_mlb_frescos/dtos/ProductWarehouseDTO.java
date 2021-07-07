package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductWarehouseDTO {

    private String productId;
    private List<WarehouseQuantityDTO> warehouses;
}
