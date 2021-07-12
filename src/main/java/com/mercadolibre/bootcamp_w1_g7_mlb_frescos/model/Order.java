package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID id;
    
    @Column
    private Double price;

    @OneToMany(mappedBy = "purchaseOrder")
    private List<ProductOrder> detailOrder = new ArrayList<>();
}
