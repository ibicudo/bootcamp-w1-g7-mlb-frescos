package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.service.order;

import java.util.List;
import java.util.UUID;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.CreateOrderRequestDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.CreateOrderResponseDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.exceptions.NotFoundException;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.Product;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.ProductOrder;

public interface OrderService {
    CreateOrderResponseDTO createOrder(CreateOrderRequestDTO createOrderRequestDTO) throws NotFoundException;

    List<ProductOrder> listProductsFromOrder( UUID orderId);

    CreateOrderResponseDTO modifyOrder(UUID orderId, CreateOrderRequestDTO createOrderRequestDTO);
}
