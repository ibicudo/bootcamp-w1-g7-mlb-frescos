package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

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
