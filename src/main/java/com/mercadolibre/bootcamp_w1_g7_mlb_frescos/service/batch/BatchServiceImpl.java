package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.service.batch;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.ExpiringProductsDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.Supervisor;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository.*;
import org.springframework.stereotype.Service;

@Service
public class BatchServiceImpl implements BatchService {

    private final BatchRepository batchRepository;

    private final WarehouseRepository warehouseRepository;

    private final SupervisorRepository supervisorRepository;

    private final SectionRepository sectionRepository;

    private final InboundOrderRepository inboundOrderRepository;

    public BatchServiceImpl(BatchRepository batchRepository, WarehouseRepository warehouseRepository, SupervisorRepository supervisorRepository, SectionRepository sectionRepository, InboundOrderRepository inboundOrderRepository) {
        this.batchRepository = batchRepository;
        this.warehouseRepository = warehouseRepository;
        this.supervisorRepository = supervisorRepository;
        this.sectionRepository = sectionRepository;
        this.inboundOrderRepository = inboundOrderRepository;
    }


    @Override
    public ExpiringProductsDTO getBatchesWithExpiringProducts(Integer quantityDays, String supervisorId) {
        return null;
    }
}
