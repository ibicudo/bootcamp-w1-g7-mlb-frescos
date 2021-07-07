package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.integration;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.ProductWarehouseDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.*;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository.BatchRepository;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository.ProductRepository;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository.WarehouseRepository;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.util.TestUniUtilsGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductOrderServiceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private BatchRepository batchRepository;

    @MockBean
    private WarehouseRepository warehouseRepository;

    private List<Section> sections = new ArrayList<>();
    private InboundOrder inboundOrder;
    private Product product;
    private List<Batch> batches;
    private ProductWarehouseDTO productWarehouseDTO;
    private List<Warehouse> warehouses;

   @BeforeEach
   void setUp(){
       sections.add(TestUniUtilsGenerator.createSection("OSAF001", "FS", 500, new Warehouse("OSAF", "Fullfillment Osasco")));
       sections.add(TestUniUtilsGenerator.createSection("CAJF001", "FS", 100, new Warehouse("CAJF", "Fullfillment Cajamar")));
       inboundOrder = TestUniUtilsGenerator.createInboundOrder();
       product = TestUniUtilsGenerator.createProduct();
       batches = TestUniUtilsGenerator.createBatchStockList();
       productWarehouseDTO = TestUniUtilsGenerator.createProductWarehouseDTO();
       warehouses = TestUniUtilsGenerator.createWarehouses();
   }

    @Test
    void getRightProductInAllWarehouses() throws Exception {

        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(warehouseRepository.findAll()).thenReturn(warehouses);
        when(batchRepository.findBatchesByProductAndWarehouse(any(UUID.class), anyString())).thenReturn(batches);

        this.mockMvc.perform(
                get("/products/" + product.getId().toString()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.warehouses.[0].warehouseCode").value("OSAF"))
                .andExpect(jsonPath("$.warehouses.[0].totalQuantity").value("500"))
                .andExpect(jsonPath("$.warehouses.[1].warehouseCode").value("CAJF"))
                .andExpect(jsonPath("$.warehouses.[1].totalQuantity").value("500"));
    }

    @Test
    void getProductsInAllWarehousesWhenProductDoesNotExist() throws Exception {

        when(productRepository.findById(product.getId())).thenReturn(Optional.empty());
        when(warehouseRepository.findAll()).thenReturn(warehouses);
        when(batchRepository.findBatchesByProductAndWarehouse(any(UUID.class), anyString())).thenReturn(batches);

        this.mockMvc.perform(
                get("/products/" + product.getId()))
                .andDo(print()).andExpect(status().isBadRequest());
    }

}
