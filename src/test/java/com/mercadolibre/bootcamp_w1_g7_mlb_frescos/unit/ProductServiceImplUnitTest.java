package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.unit;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.*;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository.*;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.service.product.ProductServiceImpl;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.util.MockitoExtension;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.util.TestUniUtilsGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplUnitTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private BatchRepository batchRepository;

    @Mock
    private WarehouseRepository warehouseRepository;

    @Mock
    private InboundOrderRepository inboundOrderRepository;
    
    @Mock
    private SectionRepository sectionRepository;


    @InjectMocks
    private ProductServiceImpl productService;

    private List<Section> sections = new ArrayList<>();
    private InboundOrder inboundOrder;
    private Product product;
    private List<Batch> batches;


    @BeforeEach
    void setUp() {
        sections.add(TestUniUtilsGenerator.createSection());
        inboundOrder = TestUniUtilsGenerator.createInboundOrder();
        product = TestUniUtilsGenerator.createProduct();
        batches = TestUniUtilsGenerator.createBatchStockList();

    }

    @Test
    void testGetRightProductInAllWarehouses() {

        batches.add(TestUniUtilsGenerator.createBatch(10.0, 5.0, 500, 500, LocalDate.of(2021, 06, 10),
                LocalDateTime.of(2021, 06, 03, 00, 00, 00), (LocalDate.of(2021, 8, 15)), 2));

        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(batchRepository.findAllByProductId(product.getId())).thenReturn(batches);

        Set<InboundOrder> inboundOrders = batches.stream().map(Batch::getInboundOrder).collect(Collectors.toSet());
        Set<Section> sections = inboundOrders.stream().map(InboundOrder::getSection).collect(Collectors.toSet());
        Set<String> sectionCodes = sections.stream().map(Section::getCode).collect(Collectors.toSet());


  //      when(sectionRepository.findAllById(sectionCodes)).thenReturn();
  //      when(sectionRepository.findAll()).






    }
}
