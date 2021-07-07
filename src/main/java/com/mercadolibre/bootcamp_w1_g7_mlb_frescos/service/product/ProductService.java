package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.service.product;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.ProductWarehouseDTO;

import java.util.UUID;

public interface ProductService {

    ProductWarehouseDTO getProductsInAllWarehouses(UUID productId);
}
