package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    @NotEmpty
    @Size(min = 2, max = 45)
    private String name;

    private String category;

    @ManyToOne
    private Seller seller;
}
