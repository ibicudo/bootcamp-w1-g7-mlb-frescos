package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos;


import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.InboundOrder;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.Product;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.Date;

public class BatchDTO {

    private Float currentTemperature;

    private Float minimumTemperature;

    private Integer initialQuality;

    private Integer currentQuality;

    private LocalDateTime manufacturingDateTime;

    private Date dueDate;

    private InboundOrder inboundOrder;

    private Product product;
}
