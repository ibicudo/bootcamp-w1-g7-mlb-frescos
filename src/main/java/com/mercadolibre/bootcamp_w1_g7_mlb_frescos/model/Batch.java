package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer batchNumber;

    @Column
    private Double currentTemperature;

    @Column
    private Double minimumTemperature;

    @Column
    private Integer initialQuantity;

    @Column
    private Integer currentQuantity;

    @Column
    private LocalDateTime manufacturingDateTime;

    @Column
    private LocalDate dueDate;

    @ManyToOne()
    private InboundOrder inboundOrder;

    @OneToOne()
    private Product product;

}
