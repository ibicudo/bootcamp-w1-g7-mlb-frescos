package com.mercadolibre.ingrid_bicudo.controller;

import java.util.List;
import java.util.UUID;

import com.mercadolibre.ingrid_bicudo.dtos.CreateOrderRequestDTO;
import com.mercadolibre.ingrid_bicudo.dtos.CreateOrderResponseDTO;
import com.mercadolibre.ingrid_bicudo.model.ProductOrder;
import com.mercadolibre.ingrid_bicudo.service.order.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("api/v1/fresh-products/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping()
    @Operation(description = "Create an Order for a client and return the price of the order")
    public ResponseEntity<CreateOrderResponseDTO> createOrder(@RequestBody CreateOrderRequestDTO createOrderRequestDTO){
        return ResponseEntity.status(201).body(orderService.createOrder(createOrderRequestDTO));
    }

    @GetMapping()
    @Operation(description = "Get a list of products of an a existing order")
    public ResponseEntity<List<ProductOrder>> listProductFromOrder(@RequestParam UUID orderId){
        return ResponseEntity.status(200).body(orderService.listProductsFromOrder(orderId));
    }
 
    @PutMapping()
    @Operation(description = "Update an existing order for a client and return the price of the order")
    public ResponseEntity<CreateOrderResponseDTO> craeteOrder(@RequestBody CreateOrderRequestDTO createOrderRequestDTO,
                                                              @RequestParam UUID orderId
    ){
        return ResponseEntity.status(201).body(orderService.modifyOrder(orderId, createOrderRequestDTO));
    }
}
