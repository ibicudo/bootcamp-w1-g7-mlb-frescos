package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InboundOrderDTO{
    public Integer orderNumber;
    public LocalDate orderDate;
    public SectionDTO section;
    public List<BatchStockDTO> batchStock;
}
