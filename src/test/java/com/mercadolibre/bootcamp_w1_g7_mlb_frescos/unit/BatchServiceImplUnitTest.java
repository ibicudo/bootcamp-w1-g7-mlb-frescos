package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.unit;


import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.BatchStockWithDueDateDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.ExpiringProductsDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.exceptions.BadRequestException;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.exceptions.NotFoundException;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.*;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository.*;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.service.batch.BatchServiceImpl;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.util.MockitoExtension;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.util.TestUniUtilsGenerator;
import org.hibernate.annotations.NotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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

    @Mock
    private InboundOrderRepository inboundOrderRepository;

    @InjectMocks
    private BatchServiceImpl batchService;

    private Supervisor supervisor;
    private List<Section> sectionList = new ArrayList<>();
    private List<InboundOrder> inboundOrders = new ArrayList<>();
    private Set<String> sectionCodesList = new HashSet<>();
    private List<Batch> batchList = new ArrayList<>();
    private BatchStockWithDueDateDTO batchStockWithDueDateDTO;
    private Warehouse warehouse;

//    @BeforeEach
//    public void setUp (){
//        sectionList.add(TestUniUtilsGenerator.createSection("OSAF001", "FS", 500, new Warehouse("OSAF", "Fullfillment Osasco")));
//        sectionList.add(TestUniUtilsGenerator.createSection("OSAF002", "RF", 500, new Warehouse("OSAF", "Fullfillment Osasco")));
//        inboundOrders.add(TestUniUtilsGenerator.createInboundOrder());
//        InboundOrder inboundOrder2 = TestUniUtilsGenerator.createInboundOrder();
//        inboundOrder2.setSection(sectionList.get(1));
//        inboundOrders.add(inboundOrder2);
//        sectionCodesList.add("OSAF001");
//        sectionCodesList.add("OSAF002");
//        BatchStockWithDueDateDTO batchStockWithDueDateDTO = TestUniUtilsGenerator.createBatchStockWithDueDate();
//        batchList = TestUniUtilsGenerator.createBatchStockList();
//        supervisor = TestUniUtilsGenerator.createSupervisor();
//        batchService = new BatchServiceImpl(batchRepository, warehouseRepository, supervisorRepository, sectionRepository, inboundOrderRepository);
//        warehouse = TestUniUtilsGenerator.createWarehouse();
//    }
//
//    @Test
//    void testGetBatchesByDueDate (){
//        //arrange
//        when(supervisorRepository.findById(supervisor.getId())).thenReturn(Optional.of(supervisor));
//        when(warehouseRepository.findById(supervisor.getWarehouse().getCode())).thenReturn(Optional.of(warehouse));
//        when(sectionRepository.findAllByWarehouseCode(warehouse.getCode())).thenReturn(sectionList);
//        when(inboundOrderRepository.findAllBySectionsCode(sectionCodesList)).thenReturn(inboundOrders);
//        Set<Integer> orderNumbers = new HashSet<>();
//        orderNumbers = inboundOrders.stream().map(InboundOrder::getOrderNumber).collect(Collectors.toSet());
//        when(batchRepository.findAllByOrderNumberFilterDate(any(),any() )).thenReturn(batchList);
//
//        //act
//        ExpiringProductsDTO response = batchService.getBatchesWithExpiringProducts(30, supervisor.getId());
//
//        //assert
//        assertThat(batchStockWithDueDateDTO).usingRecursiveComparison().isEqualTo(response.getBatchStock());
//
//    }
//
//    @Test
//    void testGetBatchesWithWarehouseInvalid(){
//        //arrange
//        when(supervisorRepository.findById(supervisor.getId())).thenReturn(Optional.of(supervisor));
//        when(warehouseRepository.findById(supervisor.getWarehouse().getCode())).thenReturn(Optional.empty());
//        when(sectionRepository.findAllByWarehouseCode(warehouse.getCode())).thenReturn(sectionList);
//        when(inboundOrderRepository.findAllBySectionsCode(sectionCodesList)).thenReturn(inboundOrders);
//        Set<Integer> orderNumbers = new HashSet<>();
//        orderNumbers = inboundOrders.stream().map(InboundOrder::getOrderNumber).collect(Collectors.toSet());
//        when(batchRepository.findAllByOrderNumberFilterDate(any(),any() )).thenReturn(batchList);
//
//        //act assert
//        assertThrows(BadRequestException.class, () -> {
//            ExpiringProductsDTO response = batchService.getBatchesWithExpiringProducts(30, supervisor.getId());
//        });
//
//    }
//
//    @Test
//    void testGetBatchesEmpty(){
//        //arrange
//        when(supervisorRepository.findById(supervisor.getId())).thenReturn(Optional.of(supervisor));
//        when(warehouseRepository.findById(supervisor.getWarehouse().getCode())).thenReturn(Optional.empty());
//        when(sectionRepository.findAllByWarehouseCode(warehouse.getCode())).thenReturn(sectionList);
//        when(inboundOrderRepository.findAllBySectionsCode(sectionCodesList)).thenReturn(inboundOrders);
//        Set<Integer> orderNumbers = new HashSet<>();
//        orderNumbers = inboundOrders.stream().map(InboundOrder::getOrderNumber).collect(Collectors.toSet());
//        List<Batch> listBatchEmpty = new ArrayList<>();
//        when(batchRepository.findAllByOrderNumberFilterDate(any(),any() )).thenReturn(batchList);
//
//        //act assert
//        assertThrows(NotFoundException.class, () -> {
//            ExpiringProductsDTO response = batchService.getBatchesWithExpiringProducts(30, supervisor.getId());
//        });
//
//    }

}
