package com.mercadolibre.ingrid_bicudo.util.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.mercadolibre.ingrid_bicudo.model.Order;
import com.mercadolibre.ingrid_bicudo.model.Product;
import com.mercadolibre.ingrid_bicudo.model.ProductOrder;

public class OrderFactory {
    static public Order getOrderThatExits(){
        List<ProductOrder> detailOrder =  new ArrayList<>();
        Product product0 = new Product();
        product0.setId(UUID.fromString("51b3b287-0b78-484c-90c3-606c4bae9401"));
        product0.setName("Alface");
        product0.setCategory("RS");
        product0.setPrice(10D);

        Product product1 = new Product();
        product1.setId(UUID.fromString("1b0d82fa-277f-4f13-a9b7-a6c4c4eec204"));
        product0.setName("Leite");
        product0.setCategory("RS");
        product0.setPrice(20D);

        Product product2 = new Product();
        product2.setId(UUID.fromString("fa0d9b2e-3eac-417e-8ee6-f26037336522"));
        product2.setName("Batata");
        product2.setCategory("FR");
        product2.setPrice(30D);
        
        ProductOrder productOrder0 = new ProductOrder();
        productOrder0.setProduct(product0);
        productOrder0.setQuantity(10);

        ProductOrder productOrder1 = new ProductOrder();
        productOrder1.setProduct(product1);
        productOrder1.setQuantity(10);

        ProductOrder productOrder2 = new ProductOrder();
        productOrder2.setProduct(product2);
        productOrder2.setQuantity(10);

        
        detailOrder.add(productOrder0);
        detailOrder.add(productOrder1);
        detailOrder.add(productOrder2);
        
        return new Order(
            UUID.fromString("1b0d82fa-277f-4f13-a9b7-a6c4c4eec204"),
            300D,
            detailOrder
        );
    }
}
