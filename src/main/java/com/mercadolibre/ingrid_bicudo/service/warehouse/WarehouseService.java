package com.mercadolibre.ingrid_bicudo.service.warehouse;

import com.mercadolibre.ingrid_bicudo.dtos.ProductWarehouseDTO;

import java.util.UUID;

public interface WarehouseService {

    ProductWarehouseDTO getProductsInAllWarehouses(UUID productId);
}
