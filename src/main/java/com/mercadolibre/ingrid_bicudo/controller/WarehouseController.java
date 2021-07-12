package com.mercadolibre.ingrid_bicudo.controller;

import com.mercadolibre.ingrid_bicudo.dtos.ProductWarehouseDTO;
import com.mercadolibre.ingrid_bicudo.service.warehouse.WarehouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controller responsible for find the product amount available in all warehouses.
 */
@RestController
@RequestMapping("api/v1/fresh-products/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    /**
     * Returns a list with the warehouse and the corresponding quantity of a specific product.
     * @param productId
     * @return ProductWarehouseDTO
     */
    @GetMapping
    @Operation(description = "Returns a list with the warehouse and the corresponding quantity of a specific product")
        public ResponseEntity<ProductWarehouseDTO> getProductsInAllWarehouses(
                @Parameter(description = "The product id to search") @RequestParam UUID productId ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(warehouseService.getProductsInAllWarehouses(productId));
    }
}
