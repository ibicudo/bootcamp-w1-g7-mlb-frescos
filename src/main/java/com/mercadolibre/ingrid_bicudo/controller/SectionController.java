package com.mercadolibre.ingrid_bicudo.controller;


import com.mercadolibre.ingrid_bicudo.dtos.SectionUpdateDTO;
import com.mercadolibre.ingrid_bicudo.model.Account;
import com.mercadolibre.ingrid_bicudo.model.Section;
import com.mercadolibre.ingrid_bicudo.security.JWTUtil;
import com.mercadolibre.ingrid_bicudo.service.account.AccountService;
import com.mercadolibre.ingrid_bicudo.service.section.SectionService;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fresh-products/section")
public class SectionController {

    private final SectionService sectionService;
    private final AccountService accountService;

    public SectionController(SectionService sectionService, AccountService accountService) {
        this.sectionService = sectionService;
        this.accountService = accountService;
    }

    @PutMapping("")
    @Operation(description = "Update an existent inbound order and return a list with the batches updated")
    public ResponseEntity<Section> updateSection(
            @Parameter(description = "The token generated in the authentication") @RequestHeader("Authorization") String token,
            @RequestBody SectionUpdateDTO sectionUpdateDTO) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(sectionService.updateCapacitySection(sectionUpdateDTO, getAccount(token)));
    }

    private Account getAccount(String token){
        Claims claim = JWTUtil.decodeJWT(token.split(" ")[1]);
        return accountService.getAccountByName(claim.getSubject());
    }
}
