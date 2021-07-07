package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.service.product;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.ProductListResponseDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.exceptions.NotFoundException;

public interface ProductService {
    ProductListResponseDTO listProducts(String filter) throws NotFoundException;
}
