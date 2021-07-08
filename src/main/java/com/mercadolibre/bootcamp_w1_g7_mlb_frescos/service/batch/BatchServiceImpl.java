package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.service.batch;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.BatchStockWithDueDateDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.ExpiringProductsDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.exceptions.BadRequestException;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.*;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class BatchServiceImpl implements BatchService {

    private final BatchRepository batchRepository;

    private final WarehouseRepository warehouseRepository;

    private final SupervisorRepository supervisorRepository;

    private final SectionRepository sectionRepository;

    private final InboundOrderRepository inboundOrderRepository;

    private final ProductRepository productRepository;

    public BatchServiceImpl(BatchRepository batchRepository, WarehouseRepository warehouseRepository, SupervisorRepository supervisorRepository, SectionRepository sectionRepository, InboundOrderRepository inboundOrderRepository, ProductRepository productRepository) {
        this.batchRepository = batchRepository;
        this.warehouseRepository = warehouseRepository;
        this.supervisorRepository = supervisorRepository;
        this.sectionRepository = sectionRepository;
        this.inboundOrderRepository = inboundOrderRepository;
        this.productRepository = productRepository;
    }


    @Override
    public ExpiringProductsDTO getBatchesWithExpiringProducts(Integer quantityDays, Account account) {

        Supervisor supervisor = supervisorRepository.findById(account.getId())
                .orElseThrow(() -> new BadRequestException("Supervisor not found"));
        Warehouse warehouse = warehouseRepository.findById(supervisor.getWarehouse().getCode())
                .orElseThrow(() -> new BadRequestException("Warehouse not found"));

        ExpiringProductsDTO result = new ExpiringProductsDTO();
        List<BatchStockWithDueDateDTO> batchStockWithDueDateDTOList = new ArrayList<>();


        LocalDate date = LocalDate.now();
        date=date.plusDays(quantityDays);

        List<Batch> batchList = batchRepository.findAllByOrderNumberFilte(date);
        batchList.stream().forEach(batch ->  {
            BatchStockWithDueDateDTO batchStockWithDueDateDTO = new BatchStockWithDueDateDTO();
            batchStockWithDueDateDTO.setDueDate(batch.getDueDate());
            batchStockWithDueDateDTO.setBatchNumber(batch.getBatchNumber());
            batchStockWithDueDateDTO.setProductId(batch.getProduct().getId().toString());
            batchStockWithDueDateDTO.setProductTypeId(batch.getProduct().getCategory());
            batchStockWithDueDateDTO.setQuantity(batch.getCurrentQuantity());

            batchStockWithDueDateDTOList.add(batchStockWithDueDateDTO);
        });

        result.setBatchStock(batchStockWithDueDateDTOList);

        return result;
    }
}
