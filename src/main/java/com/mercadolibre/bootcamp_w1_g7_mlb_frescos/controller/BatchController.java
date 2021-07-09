package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.controller;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.BatchStockDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.CreateInboundOrderDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.ExpiringProductsDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.Account;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.security.JWTUtil;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.service.account.AccountService;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.service.batch.BatchService;
import io.jsonwebtoken.Claims;
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
    private final AccountService accountService;

    public BatchController(BatchService batchService, AccountService accountService) {
        this.batchService = batchService;
        this.accountService = accountService;
    }

    @GetMapping()
    public ResponseEntity<ExpiringProductsDTO> getExpiringProductsDTO(@RequestHeader("Authorization") String token, @RequestParam Integer quantityDays) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(batchService.getBatchesWithExpiringProducts(quantityDays, getAccount(token)));
    }

    @GetMapping("/list")
    public ResponseEntity<ExpiringProductsDTO> getExpiringProductsDTO(@RequestHeader("Authorization") String token, @RequestParam Integer quantityDays, @RequestParam String category, @RequestParam (required = false) String typeOrder) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(batchService.getBatchesWithExpiringProductsWithFilters(quantityDays, category, typeOrder, getAccount(token)));
    }

    private  Account getAccount(String token){
        Claims claim = JWTUtil.decodeJWT(token.split(" ")[1]);
        return accountService.getAccountByName(claim.getSubject());
    }
}
