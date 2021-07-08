package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.unit;


import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.ExpiringProductsDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.*;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository.*;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.service.batch.BatchServiceImpl;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.service.inboundorder.InboundOrderServiceImpl;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.util.MockitoExtension;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.util.TestUniUtilsGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.*;
import java.util.stream.Collectors;

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

    @BeforeEach
    public void setUp (){
        sectionList.add(TestUniUtilsGenerator.createSection("OSAF001", "FS", 500, new Warehouse("OSAF", "Fullfillment Osasco")));
        sectionList.add(TestUniUtilsGenerator.createSection("OSAF002", "RF", 500, new Warehouse("OSAF", "Fullfillment Osasco")));
        inboundOrders.add(TestUniUtilsGenerator.createInboundOrder());
        sectionCodesList.add("OSAF001");
        sectionCodesList.add("OSAF002");
        batchList = TestUniUtilsGenerator.createBatchStockList();
        supervisor = TestUniUtilsGenerator.createSupervisor();
////        inboundOrder = TestUniUtilsGenerator.createInboundOrder();
////        inboundOrderDTO = TestUniUtilsGenerator.createInboundOrderDTO();
        batchService = new BatchServiceImpl(batchRepository, warehouseRepository, supervisorRepository, sectionRepository, inboundOrderRepository);

    }

    @Test
    void testGetBatchesByDueDate (){
        //arrange
        when(supervisorRepository.findById(supervisor.getId())).thenReturn(Optional.of(supervisor));
        when(sectionRepository.findAllByWarehouseCode(supervisor.getWarehouse().getCode())).thenReturn(sectionList);
        when(inboundOrderRepository.findAllBySectionsCode(sectionCodesList)).thenReturn(inboundOrders);
        Set<Integer> orderNumbers = new HashSet<>();
        orderNumbers = inboundOrders.stream().map(InboundOrder::getOrderNumber).collect(Collectors.toSet());
        when(batchRepository.findAllByOrderNumber(orderNumbers)).thenReturn(batchList);

        //act
        ExpiringProductsDTO expiringProductsDTO = batchService.getBatchesWithExpiringProducts(30, supervisor.getId().toString());

        //


    }

}
