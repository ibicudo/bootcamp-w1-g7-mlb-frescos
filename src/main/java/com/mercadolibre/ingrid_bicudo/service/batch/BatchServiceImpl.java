package com.mercadolibre.ingrid_bicudo.service.batch;

import com.mercadolibre.ingrid_bicudo.dtos.BatchStockWithDueDateDTO;
import com.mercadolibre.ingrid_bicudo.dtos.ExpiringProductsDTO;
import com.mercadolibre.ingrid_bicudo.exceptions.BadRequestException;
import com.mercadolibre.ingrid_bicudo.model.Account;
import com.mercadolibre.ingrid_bicudo.model.Batch;
import com.mercadolibre.ingrid_bicudo.model.Supervisor;
import com.mercadolibre.ingrid_bicudo.model.Warehouse;
import com.mercadolibre.ingrid_bicudo.repository.BatchRepository;
import com.mercadolibre.ingrid_bicudo.repository.SupervisorRepository;
import com.mercadolibre.ingrid_bicudo.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatchServiceImpl implements BatchService {

    private final BatchRepository batchRepository;

    private final WarehouseRepository warehouseRepository;

    private final SupervisorRepository supervisorRepository;

    public BatchServiceImpl(BatchRepository batchRepository, WarehouseRepository warehouseRepository, SupervisorRepository supervisorRepository) {
        this.batchRepository = batchRepository;
        this.warehouseRepository = warehouseRepository;
        this.supervisorRepository = supervisorRepository;
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

        List<Batch> batchList = batchRepository.findAllByOrderNumberFilter(warehouse.getCode(), date);
        batchList.stream().forEach(batch -> {
            BatchStockWithDueDateDTO batchStockWithDueDateDTO = new BatchStockWithDueDateDTO();
            batchStockWithDueDateDTO.setDueDate(batch.getDueDate());
            batchStockWithDueDateDTO.setBatchNumber(batch.getBatchNumber());
            batchStockWithDueDateDTO.setProductId(batch.getProduct().getId().toString());
            batchStockWithDueDateDTO.setProductTypeId(batch.getProduct().getCategory());
            batchStockWithDueDateDTO.setQuantity(batch.getCurrentQuantity());

            batchStockWithDueDateDTOList.add(batchStockWithDueDateDTO);
        });


        result.setBatchStock(getOrderList(batchStockWithDueDateDTOList, "asc"));

        return result;
    }

    @Override
    public ExpiringProductsDTO getBatchesWithExpiringProductsWithFilters(Integer quantityDays, String category, String typeOrder, Account account) {
        ExpiringProductsDTO expiringProductsDTO = getBatchesWithExpiringProducts(quantityDays, account);
        List<BatchStockWithDueDateDTO> batchStockWithDueDateDTOList = expiringProductsDTO.getBatchStock().stream().filter(batchStockWithDueDateDTO ->
            batchStockWithDueDateDTO.getProductTypeId().equals(category)
        ).collect(Collectors.toList());

        expiringProductsDTO.setBatchStock(getOrderList(batchStockWithDueDateDTOList, typeOrder));

        return expiringProductsDTO;
    }


    private List<BatchStockWithDueDateDTO> getOrderList (List<BatchStockWithDueDateDTO> batches, String typeOrder){

        if(typeOrder == null || typeOrder.equals("asc")){
            batches.sort(Comparator.comparing(BatchStockWithDueDateDTO::getDueDate));
        }else {
            batches.sort(Comparator.comparing(BatchStockWithDueDateDTO::getDueDate).reversed());
        }

        return batches;
    }
}
