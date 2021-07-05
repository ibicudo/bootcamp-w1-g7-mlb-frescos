package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.unit;


import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.BatchStockDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.InboundOrderDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.exceptions.BadRequestException;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.InboundOrder;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.Product;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.Section;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository.InboundOrderRepository;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository.ProductRepository;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository.SectionRepository;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.service.inboundorder.InboundOrderServiceImpl;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.util.MockitoExtension;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.util.TestUniUtilsGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InboundOrderServiceImplUnitTest {

    @Mock
    private InboundOrderRepository inboundOrderRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private SectionRepository sectionRepository;


    @InjectMocks
    private InboundOrderServiceImpl inboundOrderServiceImpl;

    InboundOrder inboundOrder;
    Section section;
    List<Product> products;

    @BeforeEach
    public void setUp (){
        section = TestUniUtilsGenerator.createSection();
        products = TestUniUtilsGenerator.createListProducts();
    }

    @Test
    void testCreateRightWithRightSection(){
        //arrange
        InboundOrderDTO inboundOrderDTO = TestUniUtilsGenerator.getInboundOrderDto();
        Optional<Section> sectionOptional = Optional.of(section);
        List<UUID> listProducts = new ArrayList<>();
        listProducts.add(inboundOrderDTO.getBatchStock().get(0).getProductId());
        when(sectionRepository.findById(inboundOrderDTO.getSection().getSectionCode())).thenReturn(sectionOptional);
        when(productRepository.findAllById(listProducts)).thenReturn(products);

        //act
        BatchStockDTO response = inboundOrderServiceImpl.createInboundOrder(inboundOrderDTO);

        //assert
        verify(sectionRepository, atLeastOnce()).findById(inboundOrderDTO.getSection().getSectionCode());
        verify(productRepository, atLeastOnce()).findById(inboundOrderDTO.getBatchStock().get(0).getProductId());
        verify(inboundOrderRepository, atLeastOnce()).save(inboundOrder);
        assertEquals(inboundOrderDTO.getBatchStock().get(0).getBatchNumber(), response.getBatchStock().get(0).getBatchNumber());
    }

    @Test
    void testCreateWrongWithWrongSection(){
        //arrange
        InboundOrderDTO inboundOrderDTO = TestUniUtilsGenerator.getInboundOrderDto();
        Optional<Section> sectionOptional = Optional.empty();
        List<UUID> listProducts = new ArrayList<>();
        listProducts.add(inboundOrderDTO.getBatchStock().get(0).getProductId());
        when(sectionRepository.findById(inboundOrderDTO.getSection().getSectionCode())).thenReturn(sectionOptional);
        when(productRepository.findAllById(listProducts)).thenReturn(products);

        //assert
        assertThrows(BadRequestException.class, () -> {
            BatchStockDTO response = inboundOrderServiceImpl.createInboundOrder(inboundOrderDTO);
        });
    }

    @Test
    void testCreateRightWithRightWarehouse(){
        //arrange
        InboundOrderDTO inboundOrderDTO = TestUniUtilsGenerator.getInboundOrderDto();
        Optional<Section> sectionOptional = Optional.of(section);
        List<UUID> listProducts = new ArrayList<>();
        listProducts.add(inboundOrderDTO.getBatchStock().get(0).getProductId());
        when(sectionRepository.findById(inboundOrderDTO.getSection().getSectionCode())).thenReturn(sectionOptional);
        when(productRepository.findAllById(listProducts)).thenReturn(products);

        //act
        BatchStockDTO response = inboundOrderServiceImpl.createInboundOrder(inboundOrderDTO);

        //assert
        assertEquals("CAJF" , inboundOrderDTO.getSection().getWarehouseCode());
    }

    @Test
    void testCreateWrongWithWrongWarehouse(){
        //arrange
        InboundOrderDTO inboundOrderDTO = TestUniUtilsGenerator.getInboundOrderDto();
        Optional<Section> sectionOptional = Optional.of(section);
        List<UUID> listProducts = new ArrayList<>();
        listProducts.add(inboundOrderDTO.getBatchStock().get(0).getProductId());
        when(sectionRepository.findById(inboundOrderDTO.getSection().getSectionCode())).thenReturn(sectionOptional);
        when(productRepository.findAllById(listProducts)).thenReturn(products);

        //act
        inboundOrderDTO.getSection().setWarehouseCode("DAHJ");

        //assert
        assertThrows(BadRequestException.class, () -> {
            BatchStockDTO response = inboundOrderServiceImpl.createInboundOrder(inboundOrderDTO);
        });

    }

    @Test
    void testCreateRightBatchWithProduct (){
        //arrange
        InboundOrderDTO inboundOrderDTO = TestUniUtilsGenerator.getInboundOrderDto();
        Optional<Section> sectionOptional = Optional.of(section);
        List<UUID> listProducts = new ArrayList<>();
        listProducts.add(inboundOrderDTO.getBatchStock().get(0).getProductId());
        when(sectionRepository.findById(inboundOrderDTO.getSection().getSectionCode())).thenReturn(sectionOptional);
        when(productRepository.findAllById(listProducts)).thenReturn(products);

        //act
        BatchStockDTO response = inboundOrderServiceImpl.createInboundOrder(inboundOrderDTO);

        //assert
        verify(sectionRepository, atLeastOnce()).findById(inboundOrderDTO.getSection().getSectionCode());
        verify(productRepository, atLeastOnce()).findById(inboundOrderDTO.getBatchStock().get(0).getProductId());
        verify(inboundOrderRepository, atLeastOnce()).save(inboundOrder);
        assertEquals("51b3b287-0b78-484c-90c3-606c4bae9401", response.getBatchStock().get(0).getProductId());
    }

    @Test
    void testCreateWrongBatchWithProduct (){
        //arrange
        InboundOrderDTO inboundOrderDTO = TestUniUtilsGenerator.getInboundOrderDto();
        section.setCategory("RF");
        Optional<Section> sectionOptional = Optional.of(section);
        List<UUID> listProducts = new ArrayList<>();
        listProducts.add(inboundOrderDTO.getBatchStock().get(0).getProductId());
        when(sectionRepository.findById(inboundOrderDTO.getSection().getSectionCode())).thenReturn(sectionOptional);
        when(productRepository.findAllById(listProducts)).thenReturn(products);

        //assert
        assertThrows(BadRequestException.class, () -> {
            BatchStockDTO response = inboundOrderServiceImpl.createInboundOrder(inboundOrderDTO);
        });
    }

    @Test
    void testCreateWrongBatchWithWrongCapacity (){
        //arrange
        InboundOrderDTO inboundOrderDTO = TestUniUtilsGenerator.getInboundOrderDto();
        section.setCapacity(1);
        Optional<Section> sectionOptional = Optional.of(section);
        List<UUID> listProducts = new ArrayList<>();
        listProducts.add(inboundOrderDTO.getBatchStock().get(0).getProductId());
        when(sectionRepository.findById(inboundOrderDTO.getSection().getSectionCode())).thenReturn(sectionOptional);
        when(productRepository.findAllById(listProducts)).thenReturn(products);

        //assert
        assertThrows(BadRequestException.class, () -> {
            BatchStockDTO response = inboundOrderServiceImpl.createInboundOrder(inboundOrderDTO);
        });
    }

}
