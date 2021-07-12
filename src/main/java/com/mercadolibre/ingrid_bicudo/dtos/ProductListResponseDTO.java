package com.mercadolibre.ingrid_bicudo.dtos;

import java.util.List;

import com.mercadolibre.ingrid_bicudo.model.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductListResponseDTO {
    List<Product> products;
}
