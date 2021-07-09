package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.key.ProductOrderKey;

@Entity
@Table(name = "order_product")

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrder {
    @EmbeddedId
    private ProductOrderKey id;

    @ManyToOne
    @MapsId("purchaseOrderId")
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    private Order Order;

    // TODO: verify if its ocurring
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    private Product product;

    private Integer quantity;
}
