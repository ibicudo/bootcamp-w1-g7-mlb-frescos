package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository;

import java.util.List;
import java.util.UUID;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.Batch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchRepository  extends JpaRepository<Batch, Integer> {
    @Query("FROM batch b WHERE b.product=:product")
    List<Batch> findByProduct(UUID product);
}
