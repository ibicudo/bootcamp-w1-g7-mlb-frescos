package com.mercadolibre.ingrid_bicudo.repository;

import com.mercadolibre.ingrid_bicudo.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import java.util.List;

public interface SectionRepository  extends JpaRepository<Section, String> {

    List<Section> findAllByWarehouseCode(String warehouseCode);

    Optional<Section> findByWarehouseCodeAndCategory(String warehouse, String category);
}
