package com.mercadolibre.ingrid_bicudo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateInboundOrderDTO {

    @Valid
    @NotNull(message = "The inboundOrder is required")
    private InboundOrderWithoutOrderNumberDTO inboundOrder;
}
