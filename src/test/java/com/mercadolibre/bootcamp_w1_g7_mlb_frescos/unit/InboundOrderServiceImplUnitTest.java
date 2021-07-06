package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.unit;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.*;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.exceptions.BadRequestException;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.*;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository.*;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.service.inboundorder.InboundOrderServiceImpl;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.util.MockitoExtension;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.util.TestUniUtilsGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class InboundOrderServiceImplUnitTest {

    @Mock
    private InboundOrderRepository inboundOrderRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private SectionRepository sectionRepository;

    @Mock
    private SupervisorRepository supervisorRepository;

    @Mock
    private BatchRepository batchRepository;

    @InjectMocks
    private InboundOrderServiceImpl inboundOrderServiceImpl;

    private ModelMapper modelMapper;

    InboundOrder inboundOrder;
    Section section;
    List<Product> products;
    Supervisor supervisor;
    InboundOrderDTO inboundOrderDTO;


    @BeforeEach
    public void setUp (){
        section = TestUniUtilsGenerator.createSection();
        products = TestUniUtilsGenerator.createListProducts();
        supervisor = TestUniUtilsGenerator.createSupervisor();
        inboundOrder = TestUniUtilsGenerator.createInboundOrder();
        inboundOrderDTO = TestUniUtilsGenerator.createInboundOrderDTO();
        modelMapper = TestUniUtilsGenerator.createModelMapper();
        inboundOrderServiceImpl = new InboundOrderServiceImpl(productRepository, supervisorRepository,
                 sectionRepository,  inboundOrderRepository, batchRepository, modelMapper);

    }

    @Test
    void testCreateRightWithRightSection(){
        //arrange
        CreateInboundOrderDTO createInboundOrderDTO = TestUniUtilsGenerator.getInboundOrderDto();
        Optional<Section> sectionOptional = Optional.of(section);
        Optional<Supervisor> supervisorOptional = Optional.of(supervisor);
        Set<UUID> listProducts = new HashSet<>();
        listProducts.add(createInboundOrderDTO.getInboundOrder().getBatchStock().get(0).getProductId());
        when(sectionRepository.findById(createInboundOrderDTO.getInboundOrder().getSection().getSectionCode())).thenReturn(sectionOptional);
        when(productRepository.findAllById(listProducts)).thenReturn(products);
        when(supervisorRepository.findById(supervisor.getId())).thenReturn(supervisorOptional);
        when(inboundOrderRepository.save(any(InboundOrder.class))).thenReturn(inboundOrder);
        inboundOrderDTO.getBatchStock().get(0).setBatchNumber(1);

        //act
        BatchStockDTO response = inboundOrderServiceImpl.createInboundOrder(createInboundOrderDTO);

        //assert
        assertThat(inboundOrderDTO.getBatchStock()).usingRecursiveComparison().isEqualTo(response.getBatchStock());
    }

    @Test
    void testCreateWrongIfDoesNotExistSection(){
        //arrange
        CreateInboundOrderDTO createInboundOrderDTO = TestUniUtilsGenerator.getInboundOrderDto();
        Optional<Section> sectionOptional = Optional.empty();
        Optional<Supervisor> supervisorOptional = Optional.of(supervisor);
        Set<UUID> listProducts = new HashSet<>();
        listProducts.add(createInboundOrderDTO.getInboundOrder().getBatchStock().get(0).getProductId());
        when(sectionRepository.findById(createInboundOrderDTO.getInboundOrder().getSection().getSectionCode())).thenReturn(sectionOptional);
        when(productRepository.findAllById(listProducts)).thenReturn(products);
        when(supervisorRepository.findById(supervisor.getId())).thenReturn(supervisorOptional);

        //assert
        assertThrows(BadRequestException.class, () -> {
            BatchStockDTO response = inboundOrderServiceImpl.createInboundOrder(createInboundOrderDTO);
        });
    }

    @Test
    void testCreateRightWithRightWarehouse(){
        //arrange
        CreateInboundOrderDTO createInboundOrderDTO = TestUniUtilsGenerator.getInboundOrderDto();
        Optional<Section> sectionOptional = Optional.of(section);
        Optional<Supervisor> supervisorOptional = Optional.of(supervisor);
        List<UUID> listProducts = new ArrayList<>();
        listProducts.add(createInboundOrderDTO.getInboundOrder().getBatchStock().get(0).getProductId());
        when(sectionRepository.findById(createInboundOrderDTO.getInboundOrder().getSection().getSectionCode())).thenReturn(sectionOptional);
        when(productRepository.findAllById(listProducts)).thenReturn(products);
        when(supervisorRepository.findById(supervisor.getId())).thenReturn(supervisorOptional);

        //act
        BatchStockDTO response = inboundOrderServiceImpl.createInboundOrder(createInboundOrderDTO);

        //assert
        assertEquals("CAJF" , createInboundOrderDTO.getInboundOrder().getSection().getWarehouseCode());
    }

//    @Test
//    void testCreateWrongWithWrongWarehouse(){
//        //arrange
//        InboundOrderDTO inboundOrderDTO = TestUniUtilsGenerator.getInboundOrderDto();
//        Optional<Section> sectionOptional = Optional.of(section);
//        List<UUID> listProducts = new ArrayList<>();
//        listProducts.add(inboundOrderDTO.getBatchStock().get(0).getProductId());
//        when(sectionRepository.findById(inboundOrderDTO.getSection().getSectionCode())).thenReturn(sectionOptional);
//        when(productRepository.findAllById(listProducts)).thenReturn(products);
//
//        //act
//        inboundOrderDTO.getSection().setWarehouseCode("DAHJ");
//
//        //assert
//        assertThrows(BadRequestException.class, () -> {
//            BatchStockDTO response = inboundOrderServiceImpl.createInboundOrder(inboundOrderDTO);
//        });
//
//    }
//
//    @Test
//    void testCreateRightBatchWithProduct (){
//        //arrange
//        InboundOrderDTO inboundOrderDTO = TestUniUtilsGenerator.getInboundOrderDto();
//        Optional<Section> sectionOptional = Optional.of(section);
//        List<UUID> listProducts = new ArrayList<>();
//        listProducts.add(inboundOrderDTO.getBatchStock().get(0).getProductId());
//        when(sectionRepository.findById(inboundOrderDTO.getSection().getSectionCode())).thenReturn(sectionOptional);
//        when(productRepository.findAllById(listProducts)).thenReturn(products);
//
//        //act
//        BatchStockDTO response = inboundOrderServiceImpl.createInboundOrder(inboundOrderDTO);
//
//        //assert
//        verify(sectionRepository, atLeastOnce()).findById(inboundOrderDTO.getSection().getSectionCode());
//        verify(productRepository, atLeastOnce()).findById(inboundOrderDTO.getBatchStock().get(0).getProductId());
//        verify(inboundOrderRepository, atLeastOnce()).save(inboundOrder);
//        assertEquals("51b3b287-0b78-484c-90c3-606c4bae9401", response.getBatchStock().get(0).getProductId());
//    }
//
//    @Test
//    void testCreateWrongBatchWithProduct (){
//        //arrange
//        InboundOrderDTO inboundOrderDTO = TestUniUtilsGenerator.getInboundOrderDto();
//        section.setCategory("RF");
//        Optional<Section> sectionOptional = Optional.of(section);
//        List<UUID> listProducts = new ArrayList<>();
//        listProducts.add(inboundOrderDTO.getBatchStock().get(0).getProductId());
//        when(sectionRepository.findById(inboundOrderDTO.getSection().getSectionCode())).thenReturn(sectionOptional);
//        when(productRepository.findAllById(listProducts)).thenReturn(products);
//
//        //assert
//        assertThrows(BadRequestException.class, () -> {
//            BatchStockDTO response = inboundOrderServiceImpl.createInboundOrder(inboundOrderDTO);
//        });
//    }
//
//    @Test
//    void testCreateWrongBatchWithWrongCapacity (){
//        //arrange
//        InboundOrderDTO inboundOrderDTO = TestUniUtilsGenerator.getInboundOrderDto();
//        section.setCapacity(1);
//        Optional<Section> sectionOptional = Optional.of(section);
//        List<UUID> listProducts = new ArrayList<>();
//        listProducts.add(inboundOrderDTO.getBatchStock().get(0).getProductId());
//        when(sectionRepository.findById(inboundOrderDTO.getSection().getSectionCode())).thenReturn(sectionOptional);
//        when(productRepository.findAllById(listProducts)).thenReturn(products);
//
//        //assert
//        assertThrows(BadRequestException.class, () -> {
//            BatchStockDTO response = inboundOrderServiceImpl.createInboundOrder(inboundOrderDTO);
//        });
//    }

}
