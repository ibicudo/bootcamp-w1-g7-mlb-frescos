package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository;

import java.util.UUID;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID>{
}
