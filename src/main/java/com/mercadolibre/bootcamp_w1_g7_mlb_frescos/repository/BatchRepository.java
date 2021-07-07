package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface BatchRepository  extends JpaRepository<Batch, Integer> {

    //TODO IMPLEMENTAR A QUERY?
    List<Batch> findAllByProductId(UUID productId);
}
