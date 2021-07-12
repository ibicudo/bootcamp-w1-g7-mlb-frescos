package com.mercadolibre.ingrid_bicudo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Validated
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BatchStockDTO {

    @Valid
    @NotEmpty(message = "The batchStock list cannot be empty")
    private Set<BatchDTO> batchStock;
}
