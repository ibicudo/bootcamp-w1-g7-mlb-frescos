package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.service.batch;


import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.ExpiringProductsDTO;

public interface BatchService {
    ExpiringProductsDTO getBatchesWithExpiringProducts(Integer quantityDays, String supervisorId);
}
