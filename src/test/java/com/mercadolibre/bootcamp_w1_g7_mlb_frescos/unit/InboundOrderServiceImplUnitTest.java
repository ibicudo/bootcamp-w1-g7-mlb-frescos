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
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
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
        section = TestUniUtilsGenerator.createSection("OSAF001", "FS", 500, new Warehouse("OSAF", "Fullfillment Osasco"));
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
        assertEquals("OSAF" , createInboundOrderDTO.getInboundOrder().getSection().getWarehouseCode());
    }

    @Test
    void testCreateWrongWithWrongWarehouse(){
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
        createInboundOrderDTO.getInboundOrder().getSection().setWarehouseCode("DAHJ");

        //assert
        assertThrows(BadRequestException.class, () -> {
            BatchStockDTO response = inboundOrderServiceImpl.createInboundOrder(createInboundOrderDTO);
        });

    }

    @Test
    void testCreateWrongBatchWithProduct (){
        //arrange
        CreateInboundOrderDTO createInboundOrderDTO = TestUniUtilsGenerator.getInboundOrderDto();
        section.setCategory("RF");
        Optional<Section> sectionOptional = Optional.of(section);
        List<UUID> listProducts = new ArrayList<>();
        listProducts.add(createInboundOrderDTO.getInboundOrder().getBatchStock().get(0).getProductId());
        when(sectionRepository.findById(createInboundOrderDTO.getInboundOrder().getSection().getSectionCode())).thenReturn(sectionOptional);
        when(productRepository.findAllById(listProducts)).thenReturn(products);

        //assert
        assertThrows(BadRequestException.class, () -> {
            BatchStockDTO response = inboundOrderServiceImpl.createInboundOrder(createInboundOrderDTO);
        });
    }

    @Test
    void testCreateWrongBatchWithWrongCapacity (){
        //arrange
        CreateInboundOrderDTO createInboundOrderDTO = TestUniUtilsGenerator.getInboundOrderDto();
        section.setCapacity(1);
        Optional<Section> sectionOptional = Optional.of(section);
        List<UUID> listProducts = new ArrayList<>();
        listProducts.add(createInboundOrderDTO.getInboundOrder().getBatchStock().get(0).getProductId());
        when(sectionRepository.findById(createInboundOrderDTO.getInboundOrder().getSection().getSectionCode())).thenReturn(sectionOptional);
        when(productRepository.findAllById(listProducts)).thenReturn(products);

        //assert
        assertThrows(BadRequestException.class, () -> {
            BatchStockDTO response = inboundOrderServiceImpl.createInboundOrder(createInboundOrderDTO);
        });
    }

    @Test
    void testRightUpdateInboundOrder() {
        //arrange
        UpdateInboundOrderDTO updateInboundOrderDTO = TestUniUtilsGenerator.createUpdateInboundOrderDTO();
        Optional<InboundOrder> inboundOrderOptional = Optional.of(inboundOrder);
        Optional<Supervisor> supervisorOptional = Optional.of(supervisor);
        Optional<Section> sectionOptional = Optional.of(section);
        List<Batch> batches = TestUniUtilsGenerator.createBatchStockList();
        Set<Integer> batchNumbers = updateInboundOrderDTO.getInboundOrder().getBatchStock().stream().map(BatchDTO::getBatchNumber).collect(Collectors.toSet());
        Set<UUID> listProducts = new HashSet<>();
        listProducts.add(updateInboundOrderDTO.getInboundOrder().getBatchStock().get(0).getProductId());

        when(inboundOrderRepository.findById(updateInboundOrderDTO.getInboundOrder().getOrderNumber())).thenReturn(inboundOrderOptional);
        when(supervisorRepository.findById(supervisor.getId())).thenReturn(supervisorOptional);
        when(sectionRepository.findById(updateInboundOrderDTO.getInboundOrder().getSection().getSectionCode())).thenReturn(sectionOptional);
        when(batchRepository.findAllById(batchNumbers)).thenReturn(batches);
        when(productRepository.findAllById(listProducts)).thenReturn(products);
        inboundOrderDTO.getBatchStock().get(0).setBatchNumber(1);
        when(inboundOrderRepository.save(any(InboundOrder.class))).thenReturn(inboundOrder);

        //act
        BatchStockDTO response = inboundOrderServiceImpl.updateInboundOrder(updateInboundOrderDTO);

        //assert
        verify(inboundOrderRepository, Mockito.times(1)).save(any(InboundOrder.class));
        assertThat(inboundOrderDTO.getBatchStock()).usingRecursiveComparison().isEqualTo(response.getBatchStock());
    }

    @Test
    void testWrongUpdateInboundOrderWhenInboundOrderDoesNotExists() {
        //arrange
        UpdateInboundOrderDTO updateInboundOrderDTO = TestUniUtilsGenerator.createUpdateInboundOrderDTO();
        Optional<InboundOrder> inboundOrderOptional = Optional.empty();

        when(inboundOrderRepository.findById(updateInboundOrderDTO.getInboundOrder().getOrderNumber())).thenReturn(inboundOrderOptional);

        //assert
        assertThrows(BadRequestException.class, () -> {
            inboundOrderServiceImpl.updateInboundOrder(updateInboundOrderDTO);
        });
    }

    @Test
    void testWrongUpdateInboundOrderWhenSupervisorDoesNotExists() {
        //arrange
        UpdateInboundOrderDTO updateInboundOrderDTO = TestUniUtilsGenerator.createUpdateInboundOrderDTO();
        Optional<InboundOrder> inboundOrderOptional = Optional.of(inboundOrder);
        Optional<Supervisor> supervisorOptional = Optional.empty();

        when(inboundOrderRepository.findById(updateInboundOrderDTO.getInboundOrder().getOrderNumber())).thenReturn(inboundOrderOptional);
        when(supervisorRepository.findById(supervisor.getId())).thenReturn(supervisorOptional);

        //assert
        assertThrows(BadRequestException.class, () -> {
            inboundOrderServiceImpl.updateInboundOrder(updateInboundOrderDTO);
        });
    }

    @Test
    void testWrongUpdateInboundOrderWhenSupervisorDoesNotMatchWarehouse() {
        //arrange
        UpdateInboundOrderDTO updateInboundOrderDTO = TestUniUtilsGenerator.createUpdateInboundOrderDTO();
        inboundOrder.setSupervisor(TestUniUtilsGenerator.createOtherSupervisor());
        Optional<InboundOrder> inboundOrderOptional = Optional.of(inboundOrder);
        Optional<Supervisor> supervisorOptional = Optional.of(supervisor);
        Optional<Section> sectionOptional = Optional.of(section);
        Set<UUID> listProducts = new HashSet<>();
        listProducts.add(updateInboundOrderDTO.getInboundOrder().getBatchStock().get(0).getProductId());

        when(inboundOrderRepository.findById(updateInboundOrderDTO.getInboundOrder().getOrderNumber())).thenReturn(inboundOrderOptional);

        when(supervisorRepository.findById(supervisor.getId())).thenReturn(supervisorOptional);
        when(sectionRepository.findById(updateInboundOrderDTO.getInboundOrder().getSection().getSectionCode())).thenReturn(sectionOptional);
        when(productRepository.findAllById(listProducts)).thenReturn(products);

        //assert
        assertThrows(BadRequestException.class, () -> {
            inboundOrderServiceImpl.updateInboundOrder(updateInboundOrderDTO);
        });
    }

    @Test
    void testWrongUpdateInboundOrderWhenBatchDoesNotBelongToInboundOrder() {
        //arrange
        UpdateInboundOrderDTO updateInboundOrderDTO = TestUniUtilsGenerator.createUpdateInboundOrderDTO();
        updateInboundOrderDTO.getInboundOrder().setOrderNumber(2);
        Optional<InboundOrder> inboundOrderOptional = Optional.of(inboundOrder);
        Optional<Supervisor> supervisorOptional = Optional.of(supervisor);
        Optional<Section> sectionOptional = Optional.of(section);
        Set<UUID> listProducts = new HashSet<>();
        listProducts.add(updateInboundOrderDTO.getInboundOrder().getBatchStock().get(0).getProductId());

        when(inboundOrderRepository.findById(updateInboundOrderDTO.getInboundOrder().getOrderNumber())).thenReturn(inboundOrderOptional);
        when(supervisorRepository.findById(supervisor.getId())).thenReturn(supervisorOptional);
        when(sectionRepository.findById(updateInboundOrderDTO.getInboundOrder().getSection().getSectionCode())).thenReturn(sectionOptional);
        when(productRepository.findAllById(listProducts)).thenReturn(products);

        //assert
        assertThrows(BadRequestException.class, () -> {
            inboundOrderServiceImpl.updateInboundOrder(updateInboundOrderDTO);
        });
    }

}
