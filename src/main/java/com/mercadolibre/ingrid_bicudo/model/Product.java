package com.mercadolibre.ingrid_bicudo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID id;

    @Column(nullable = false, length = 45)
    @NotEmpty
    private String name;

    @Column(nullable = false, length = 45)
    private String category;

    @ManyToOne()
    private Seller seller;

    @Column
    private Double price;
}
