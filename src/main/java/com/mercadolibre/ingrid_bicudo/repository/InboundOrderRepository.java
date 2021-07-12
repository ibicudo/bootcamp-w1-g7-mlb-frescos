package com.mercadolibre.ingrid_bicudo.repository;

import com.mercadolibre.ingrid_bicudo.model.InboundOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InboundOrderRepository extends JpaRepository<InboundOrder, Integer> {

}
