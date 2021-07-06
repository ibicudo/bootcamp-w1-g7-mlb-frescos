package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.controller;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.*;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.service.inboundorder.InboundOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("inboundorder")
public class InboundOrderController {

    private final InboundOrderService inboundOrderService;

    public InboundOrderController(InboundOrderService inboundOrderService) {
        this.inboundOrderService = inboundOrderService;
    }

    @PostMapping()
    public ResponseEntity<BatchStockDTO> createInboundOrder(@Valid @RequestBody CreateInboundOrderDTO createInboundOrderDTO) {
        InboundOrderWithoutOrderNumberDTO inboundOrderDTO = createInboundOrderDTO.getInboundOrder();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(inboundOrderService.createInboundOrder(createInboundOrderDTO));
    }

    @PutMapping()
    public ResponseEntity<BatchStockDTO> updateInboundOrder(@Valid @RequestBody UpdateInboundOrderDTO updateInboundOrderDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(inboundOrderService.updateInboundOrder(updateInboundOrderDTO));
    }
}
