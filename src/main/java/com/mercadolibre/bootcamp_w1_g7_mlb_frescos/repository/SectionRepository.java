package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SectionRepository extends JpaRepository<Section, String> {

    Optional<Section> findByWarehouseCodeAndCategory(String warehouse, String category);
}
