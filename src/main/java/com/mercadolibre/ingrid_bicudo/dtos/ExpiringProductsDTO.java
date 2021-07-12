package com.mercadolibre.ingrid_bicudo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import java.util.List;


@Validated
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpiringProductsDTO {

    @NotEmpty(message = "The batchStock list cannot be empty")
    private List<BatchStockWithDueDateDTO> batchStock;
}
