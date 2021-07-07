package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.controller;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.BatchStockDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.CreateInboundOrderDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.ProductWarehouseDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.service.product.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductWarehouseDTO> getProductsInAllWarehouses(@PathVariable UUID productId ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.getProductsInAllWarehouses(productId));
    }
}
