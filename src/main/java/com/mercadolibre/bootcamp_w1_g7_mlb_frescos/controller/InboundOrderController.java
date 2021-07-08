package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.controller;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.BatchStockDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.CreateInboundOrderDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.UpdateInboundOrderDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.Account;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.security.JWTUtil;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.service.account.AccountService;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.service.inboundorder.InboundOrderService;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("inboundorder")
public class InboundOrderController {

    private final InboundOrderService inboundOrderService;

    private final AccountService accountService;

    public InboundOrderController(InboundOrderService inboundOrderService, AccountService accountService) {
        this.inboundOrderService = inboundOrderService;
        this.accountService = accountService;
    }

    @PostMapping()
    public ResponseEntity<BatchStockDTO> createInboundOrder(
            @RequestHeader("Authorization") String token,
            @Valid @RequestBody CreateInboundOrderDTO createInboundOrderDTO) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(inboundOrderService.createInboundOrder(createInboundOrderDTO, getAccount(token)));
    }

    @PutMapping()
    public ResponseEntity<BatchStockDTO> updateInboundOrder(
            @RequestHeader("Authorization") String token,
            @Valid @RequestBody UpdateInboundOrderDTO updateInboundOrderDTO) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(inboundOrderService.updateInboundOrder(updateInboundOrderDTO, getAccount(token)));
    }

    private  Account getAccount(String token){
        Claims claim = JWTUtil.decodeJWT(token.split(" ")[1]);
        return accountService.getAccountByName(claim.getSubject());
    }
}