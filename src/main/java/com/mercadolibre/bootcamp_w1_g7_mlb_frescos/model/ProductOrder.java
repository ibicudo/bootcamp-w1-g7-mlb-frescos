package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

import javax.persistence.*;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "order_product")

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrder {
    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID id;

    @ManyToOne
    @MapsId("purchaseOrderId")
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    private Order order;

    // TODO: verify if its ocurring
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    private Product product;

    private int quantity;
}
