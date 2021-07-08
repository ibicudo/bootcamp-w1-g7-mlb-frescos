package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;


public interface BatchRepository  extends JpaRepository<Batch, Integer> {

    List<Batch> findAllByOrderNumber(Set<Integer> orderNumber);

}
