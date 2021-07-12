package com.mercadolibre.ingrid_bicudo.service.batch;


import com.mercadolibre.ingrid_bicudo.dtos.ExpiringProductsDTO;
import com.mercadolibre.ingrid_bicudo.model.Account;

public interface BatchService {
    ExpiringProductsDTO getBatchesWithExpiringProducts(Integer quantityDays, Account account);
    ExpiringProductsDTO getBatchesWithExpiringProductsWithFilters(Integer quantityDays, String category, String asc,  Account account);
}
