package com.mercadolibre.ingrid_bicudo.repository;

import com.mercadolibre.ingrid_bicudo.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SellerRepository extends JpaRepository<Seller, UUID> {
}
