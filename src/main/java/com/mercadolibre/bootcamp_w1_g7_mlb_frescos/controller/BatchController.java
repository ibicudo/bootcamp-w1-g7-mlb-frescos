package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.controller;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.BatchStockDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.CreateInboundOrderDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.ExpiringProductsDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.Account;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.service.batch.BatchService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("due-date")
public class BatchController {

    private final BatchService batchService;

    public BatchController(BatchService batchService) {
        this.batchService = batchService;
    }

    @GetMapping()
    public ResponseEntity<ExpiringProductsDTO> getExpiringProductsDTO(@RequestParam Integer quantityDays) {
        //TODO Autenticacao
        Account account = new Account();
        account.setId(UUID.fromString("04f55f2c-f769-46fb-bf9c-08b05b51d814"));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(batchService.getBatchesWithExpiringProducts(quantityDays,account));
    }

    @GetMapping("/list")
    public ResponseEntity<ExpiringProductsDTO> getExpiringProductsDTO(@RequestParam Integer quantityDays, @RequestParam String category, @RequestParam (required = false) String typeOrder) {
        //TODO Autenticacao
        Account account = new Account();
        account.setId(UUID.fromString("04f55f2c-f769-46fb-bf9c-08b05b51d814"));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(batchService.getBatchesWithExpiringProductsWithFilters(quantityDays, category, typeOrder,account));
    }
}
