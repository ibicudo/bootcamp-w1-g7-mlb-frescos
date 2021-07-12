package com.mercadolibre.ingrid_bicudo.service.product;

import com.mercadolibre.ingrid_bicudo.dtos.ProductListResponseDTO;
import com.mercadolibre.ingrid_bicudo.exceptions.NotFoundException;

public interface ProductService {
    ProductListResponseDTO listProducts(String filter) throws NotFoundException;
}
