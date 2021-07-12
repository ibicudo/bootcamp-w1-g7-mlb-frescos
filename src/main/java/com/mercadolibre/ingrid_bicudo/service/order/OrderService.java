package com.mercadolibre.ingrid_bicudo.service.order;

import java.util.List;
import java.util.UUID;

import com.mercadolibre.ingrid_bicudo.dtos.CreateOrderRequestDTO;
import com.mercadolibre.ingrid_bicudo.dtos.CreateOrderResponseDTO;
import com.mercadolibre.ingrid_bicudo.exceptions.NotFoundException;
import com.mercadolibre.ingrid_bicudo.model.ProductOrder;

public interface OrderService {
    CreateOrderResponseDTO createOrder(CreateOrderRequestDTO createOrderRequestDTO) throws NotFoundException;

    List<ProductOrder> listProductsFromOrder( UUID orderId);

    CreateOrderResponseDTO modifyOrder(UUID orderId, CreateOrderRequestDTO createOrderRequestDTO);
}
