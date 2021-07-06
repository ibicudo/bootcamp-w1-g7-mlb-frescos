package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.controller;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.*;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.service.inboundorder.InboundOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

@Validated
@RestController
@RequestMapping
public class InboundOrderController {

    private final InboundOrderService inboundOrderService;

    public InboundOrderController(InboundOrderService inboundOrderService) {
        this.inboundOrderService = inboundOrderService;
    }

    @PostMapping("inboundorder")
    public ResponseEntity<BatchStockDTO> createInboundOrder(@RequestBody CreateInboundOrderDTO createInboundOrderDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(inboundOrderService.createInboundOrder(createInboundOrderDTO));
    }

    @PutMapping("inboundorder")
    public ResponseEntity<BatchStockDTO> updateInboundOrder(@RequestBody UpdateInboundOrderDTO updateInboundOrderDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(inboundOrderService.updateInboundOrder(updateInboundOrderDTO));
    }

    @GetMapping("list")
    public ResponseEntity<ProductBatchStockDTO> listProductBatchStock(@RequestParam UUID productId,
                                                                      @RequestParam(required = false)
                                                                      @Nullable String sortParam) {
        //TODO pegar id do supervisor pelo token
        UUID supervisorId = UUID.fromString("04f55f2c-f769-46fb-bf9c-08b05b51d814");
        return ResponseEntity.status(HttpStatus.OK)
                .body(inboundOrderService.listProductBatchStock(productId, supervisorId, sortParam));
    }
}
