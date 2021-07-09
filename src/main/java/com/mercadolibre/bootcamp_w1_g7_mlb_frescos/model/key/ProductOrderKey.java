package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.key;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrderKey implements Serializable{
    @Column(name = "purchase_order_id")
    @Type(type = "uuid-char")
    private UUID purchaseOrderId;
    
    @Column(name = "product_id")
    @Type(type = "uuid-char")
    private UUID productId;       
}
