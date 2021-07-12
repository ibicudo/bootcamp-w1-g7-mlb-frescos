package com.mercadolibre.ingrid_bicudo.unit;


import com.mercadolibre.ingrid_bicudo.dtos.BatchStockWithDueDateDTO;
import com.mercadolibre.ingrid_bicudo.dtos.ExpiringProductsDTO;
import com.mercadolibre.ingrid_bicudo.exceptions.BadRequestException;
import com.mercadolibre.ingrid_bicudo.model.*;
import com.mercadolibre.ingrid_bicudo.repository.*;
import com.mercadolibre.ingrid_bicudo.service.batch.BatchServiceImpl;
import com.mercadolibre.ingrid_bicudo.util.MockitoExtension;
import com.mercadolibre.ingrid_bicudo.util.TestUtilsGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BatchServiceImplUnitTest {

    @Mock
    private BatchRepository batchRepository;

    @Mock
    private WarehouseRepository warehouseRepository;

    @Mock
    private SupervisorRepository supervisorRepository;

    @Mock
    private SectionRepository sectionRepository;

    @InjectMocks
    private BatchServiceImpl batchService;

    private Supervisor supervisor;
    private List<Section> sectionList = new ArrayList<>();
    private List<InboundOrder> inboundOrders = new ArrayList<>();
    private Set<String> sectionCodesList = new HashSet<>();
    private List<Batch> batchList = new ArrayList<>();
    private List<BatchStockWithDueDateDTO> batchStockWithDueDateDTO = new ArrayList<>();
    private Warehouse warehouse;
    LocalDate date = LocalDate.now();
    private Account account = new Account();

    @BeforeEach
    public void setUp (){
        sectionList.add(TestUtilsGenerator.createSection("OSAF001", "FS", 500, new Warehouse("OSAF", "Fullfillment Osasco")));
        sectionList.add(TestUtilsGenerator.createSection("OSAF002", "RF", 500, new Warehouse("OSAF", "Fullfillment Osasco")));
        inboundOrders.add(TestUtilsGenerator.createInboundOrder());
        InboundOrder inboundOrder2 = TestUtilsGenerator.createInboundOrder();
        inboundOrder2.setSection(sectionList.get(1));
        inboundOrders.add(inboundOrder2);
        sectionCodesList.add("OSAF001");
        sectionCodesList.add("OSAF002");
        batchStockWithDueDateDTO.add(TestUtilsGenerator.createBatchStockWithDueDate(LocalDate.of(2021, 8, 06),1, "51b3b287-0b78-484c-90c3-606c4bae9401", 500, "FS"));
        batchList = TestUtilsGenerator.createBatchStockList();
        supervisor = TestUtilsGenerator.createSupervisor();
        batchService = new BatchServiceImpl(batchRepository, warehouseRepository, supervisorRepository);
        warehouse = TestUtilsGenerator.createWarehouse();
        date= date.plusDays(30);
        account.setId(supervisor.getId());
    }

    @Test
    void testGetBatchesByDueDate (){
        //arrange
        when(supervisorRepository.findById(supervisor.getId())).thenReturn(Optional.of(supervisor));
        when(warehouseRepository.findById(supervisor.getWarehouse().getCode())).thenReturn(Optional.of(warehouse));
        when(sectionRepository.findAllByWarehouseCode(warehouse.getCode())).thenReturn(sectionList);
        when(batchRepository.findAllByOrderNumberFilter(warehouse.getCode(), date)).thenReturn(batchList);

        //act
        ExpiringProductsDTO response = batchService.getBatchesWithExpiringProducts(30, account);

        //assert
        assertThat(batchStockWithDueDateDTO).usingRecursiveComparison().isEqualTo(response.getBatchStock());

    }

    @Test
    void testGetBatchesWithInvalidSupervisor(){
        //arrange
        account.setId(UUID.fromString("b20c0c2d-f378-4d7c-b965-e8a6a128c948"));
        when(supervisorRepository.findById(supervisor.getId())).thenReturn(Optional.empty());
        when(warehouseRepository.findById(supervisor.getWarehouse().getCode())).thenReturn(Optional.of(warehouse));
        when(sectionRepository.findAllByWarehouseCode(warehouse.getCode())).thenReturn(sectionList);
        when(batchRepository.findAllByOrderNumberFilter(warehouse.getCode(), date)).thenReturn(batchList);


        //act assert
        assertThrows(BadRequestException.class, () -> {
            batchService.getBatchesWithExpiringProducts(30, account);
        });

    }

    @Test
    void testGetBatchesWithInvalidWarehouse(){
        //arrange
        //arrange
        when(supervisorRepository.findById(supervisor.getId())).thenReturn(Optional.of(supervisor));
        when(warehouseRepository.findById(supervisor.getWarehouse().getCode())).thenReturn(Optional.empty());
        when(sectionRepository.findAllByWarehouseCode(warehouse.getCode())).thenReturn(sectionList);
        when(batchRepository.findAllByOrderNumberFilter(warehouse.getCode(), date)).thenReturn(batchList);

        //act assert
        assertThrows(BadRequestException.class, () -> {
            batchService.getBatchesWithExpiringProducts(30, account);
        });

    }

    @Test
    void testgetBatchesWithExpiringProductsWithFilters (){
        //arrange
        when(supervisorRepository.findById(supervisor.getId())).thenReturn(Optional.of(supervisor));
        when(warehouseRepository.findById(supervisor.getWarehouse().getCode())).thenReturn(Optional.of(warehouse));
        when(sectionRepository.findAllByWarehouseCode(warehouse.getCode())).thenReturn(sectionList);
        when(batchRepository.findAllByOrderNumberFilter(warehouse.getCode(), date)).thenReturn(batchList);

        //act
        ExpiringProductsDTO response = batchService.getBatchesWithExpiringProductsWithFilters(30, "FS", "asc", account);

        //assert
        assertThat(batchStockWithDueDateDTO).usingRecursiveComparison().isEqualTo(response.getBatchStock());
    }


}
