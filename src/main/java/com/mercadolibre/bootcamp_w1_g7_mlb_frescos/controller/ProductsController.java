package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.controller;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.ProductListResponseDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.service.product.ProductService;

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
