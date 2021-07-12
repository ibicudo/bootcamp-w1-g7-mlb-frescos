package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.unit.factory;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.Order;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.Product;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.ProductOrder;

public class OrderFactory {
    static public Order getOrderThatExits(){
        List<ProductOrder> detailOrder =  new ArrayList<>();
        Product product0 = new Product();
        product0.setName("Alface");
        product0.setCategory("RS");
        product0.setPrice(10D);

        Product product1 = new Product();
        product0.setName("Leite");
        product0.setCategory("RS");
        product0.setPrice(20D);

        Product product2 = new Product();
        product0.setName("Batata");
        product0.setCategory("FR");
        product0.setPrice(30D);
        
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
            // TODO: set uuid for this order
            UUID.fromString(""),
            600D,
            detailOrder
        );
    }
}
