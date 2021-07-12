package com.mercadolibre.ingrid_bicudo.repository;

import com.mercadolibre.ingrid_bicudo.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, String> {
}
