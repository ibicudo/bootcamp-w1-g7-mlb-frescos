package com.mercadolibre.ingrid_bicudo.repository;

import com.mercadolibre.ingrid_bicudo.model.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SupervisorRepository extends JpaRepository<Supervisor, UUID> {
}
