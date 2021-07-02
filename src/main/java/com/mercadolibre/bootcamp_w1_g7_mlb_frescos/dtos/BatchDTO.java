package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BatchDTO {

    public Integer batchNumber;
    public String productId;
    public Double currentTemperature;
    public Double minimumTemperature;
    public Integer initialQuantity;
    public Integer currentQuantity;
    public LocalDate manufacturingDate;
    public LocalDateTime manufacturingTime;
    public LocalDate dueDate;

}
