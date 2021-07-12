package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.util.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.CreateOrderRequestDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.ProductOrderDTO;

public class CreateOrderRequestDTOFactory {
    static public CreateOrderRequestDTO getOrderWithLegalValues(){
        List<ProductOrderDTO> list = new ArrayList<>();
        list.add(new ProductOrderDTO(UUID.fromString("51b3b287-0b78-484c-90c3-606c4bae9401"),10));
        list.add(new ProductOrderDTO(UUID.fromString("1b0d82fa-277f-4f13-a9b7-a6c4c4eec204"), 10)) ;
        list.add(new ProductOrderDTO(UUID.fromString("fa0d9b2e-3eac-417e-8ee6-f26037336522"), 10)); 
        return new CreateOrderRequestDTO(list);   
    }

    static public CreateOrderRequestDTO getOrderWithLegalValues2(){
        List<ProductOrderDTO> list = new ArrayList<>();
        list.add(new ProductOrderDTO(UUID.fromString("51b3b287-0b78-484c-90c3-606c4bae9401"),10));
        list.add(new ProductOrderDTO(UUID.fromString("1b0d82fa-277f-4f13-a9b7-a6c4c4eec204"), 10)) ;
        return new CreateOrderRequestDTO(list);   
    }

    static public CreateOrderRequestDTO getOrderProductDoesNotExists(){
        List<ProductOrderDTO> list = new ArrayList<>();
        list.add(new ProductOrderDTO(UUID.randomUUID(),10));
        return new CreateOrderRequestDTO(list);   
    }

    static public CreateOrderRequestDTO getOrderNotEnoughProducts() {
        List<ProductOrderDTO> list = new ArrayList<>();
        list.add(new ProductOrderDTO(UUID.fromString("51b3b287-0b78-484c-90c3-606c4bae9401"), 10000));
        return new CreateOrderRequestDTO(list);   
    }

    static public CreateOrderRequestDTO getOrderWithNegativeProductQuantity(){
        List<ProductOrderDTO> list = new ArrayList<>();
        list.add(new ProductOrderDTO(UUID.fromString("51b3b287-0b78-484c-90c3-606c4bae9401"), -4));
        return new CreateOrderRequestDTO(list);
    }
}
