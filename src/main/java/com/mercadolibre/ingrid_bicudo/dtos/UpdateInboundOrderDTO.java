package com.mercadolibre.ingrid_bicudo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateInboundOrderDTO {

    @Valid
    private InboundOrderDTO inboundOrder;
}
