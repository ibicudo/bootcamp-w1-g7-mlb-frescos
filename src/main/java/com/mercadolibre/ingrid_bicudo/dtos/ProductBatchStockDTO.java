package com.mercadolibre.ingrid_bicudo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductBatchStockDTO {

    private SectionDTO section;

    private UUID productId;

    private List<BatchInfoDTO> batchStock;
}
