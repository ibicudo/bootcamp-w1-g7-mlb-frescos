package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

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
    private Float currentTemperature;

    @Column
    private Float minimumTemperature;

    @Column
    private Integer initialQuality;

    @Column
    private Integer currentQuality;

    @Column
    private LocalDateTime manufacturingDateTime;

    @Column
    private Date dueDate;

    @ManyToOne
    private InboundOrder inboundOrder;

    @OneToOne
    private Product product;

}
