package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BatchDTO {

    public Integer batchNumber;
    public UUID productId;
    public Double currentTemperature;
    public Double minimumTemperature;
    public Integer initialQuantity;
    public Integer currentQuantity;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public LocalDate manufacturingDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime manufacturingTime;
    public LocalDate dueDate;

}
