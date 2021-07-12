package com.mercadolibre.ingrid_bicudo.controller;

import com.mercadolibre.ingrid_bicudo.dtos.ProductListResponseDTO;
import com.mercadolibre.ingrid_bicudo.service.product.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("api/v1/fresh-products/")
public class ProductsController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    ResponseEntity<ProductListResponseDTO> getMethodName(@RequestParam(required=false) String filter) {
        return ResponseEntity.status(200).body(productService.listProducts(filter));
    }
}
