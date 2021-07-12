package com.mercadolibre.ingrid_bicudo.repository;

import java.util.UUID;

import com.mercadolibre.ingrid_bicudo.model.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID>{
}
