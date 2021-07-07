package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.integration;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.BatchDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.UpdateInboundOrderDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.Batch;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.InboundOrder;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.Supervisor;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository.BatchRepository;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository.InboundOrderRepository;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository.SupervisorRepository;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.util.TestUtilsGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class InboundOrderServiceImplIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    InboundOrderRepository inboundOrderRepository;

    @MockBean
    SupervisorRepository supervisorRepository;

    @MockBean
    BatchRepository batchRepository;

    InboundOrder inboundOrder;
    Supervisor supervisor;

    @BeforeEach
    void setUp(){
        inboundOrder = TestUtilsGenerator.createInboundOrder();
        supervisor = TestUtilsGenerator.createSupervisor();
    }

    @Test
    void createInboundOrderWithOneBatch() throws Exception {
        when(supervisorRepository.findById(supervisor.getId())).thenReturn(Optional.of(supervisor));
        when(inboundOrderRepository.save(any(InboundOrder.class))).thenReturn(inboundOrder);
        String request = TestUtilsGenerator.createRequestOneBatch();
        this.mockMvc.perform(
                post("/inboundorder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("$.batchStock.[0].currentTemperature").value(10.0))
                .andExpect(jsonPath("$.batchStock.[0].minimumTemperature").value(5.0))
                .andExpect(jsonPath("$.batchStock.[0].initialQuantity").value(500));

    }

    @Test
    void createInboundOrderWithTwoBatch() throws Exception {
        inboundOrder = TestUtilsGenerator.createInboundOrderWithTwoBatches();
        when(supervisorRepository.findById(supervisor.getId())).thenReturn(Optional.of(supervisor));
        when(inboundOrderRepository.save(any(InboundOrder.class))).thenReturn(inboundOrder);
        String request = TestUtilsGenerator.createRequestTwoBatches();
        this.mockMvc.perform(
                post("/inboundorder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("$.batchStock.[0].currentTemperature").value(10.0))
                .andExpect(jsonPath("$.batchStock.[0].minimumTemperature").value(5.0))
                .andExpect(jsonPath("$.batchStock.[1].currentTemperature").value(10.0))
                .andExpect(jsonPath("$.batchStock.[1].minimumTemperature").value(5.0));

    }

    @Test
    void updateInboundOrderWithOneBatch() throws Exception {
        UpdateInboundOrderDTO updateInboundOrderDTO = TestUtilsGenerator.createUpdateInboundOrderDTO();
        List<Batch> batches = TestUtilsGenerator.createBatchStockList();
        Set<Integer> batchNumbers = updateInboundOrderDTO.getInboundOrder().getBatchStock().stream().map(BatchDTO::getBatchNumber).collect(Collectors.toSet());
        when(inboundOrderRepository.findById(inboundOrder.getOrderNumber())).thenReturn(Optional.of(inboundOrder));
        when(inboundOrderRepository.save(any(InboundOrder.class))).thenReturn(inboundOrder);
        when(supervisorRepository.findById(supervisor.getId())).thenReturn(Optional.of(supervisor));
        when(batchRepository.findAllById(batchNumbers)).thenReturn(batches);


        String request = TestUtilsGenerator.updateRequestOneBatch();
        this.mockMvc.perform(
                put("/inboundorder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("$.batchStock.[0].currentTemperature").value(10.0))
                .andExpect(jsonPath("$.batchStock.[0].minimumTemperature").value(5.0));

    }

    @Test
    void updateInboundOrderWithTwoBatches() throws Exception {
        UpdateInboundOrderDTO updateInboundOrderDTO = TestUtilsGenerator.createUpdateInboundOrderDTO();
        List<Batch> batches = TestUtilsGenerator.createBatchStockList();
        Set<Integer> batchNumbers = updateInboundOrderDTO.getInboundOrder().getBatchStock().stream().map(BatchDTO::getBatchNumber).collect(Collectors.toSet());
        inboundOrder = TestUtilsGenerator.createInboundOrderWithTwoBatches();
        when(inboundOrderRepository.findById(inboundOrder.getOrderNumber())).thenReturn(Optional.of(inboundOrder));
        when(inboundOrderRepository.save(any(InboundOrder.class))).thenReturn(inboundOrder);
        when(supervisorRepository.findById(supervisor.getId())).thenReturn(Optional.of(supervisor));
        when(batchRepository.findAllById(batchNumbers)).thenReturn(batches);


        String request = TestUtilsGenerator.updateRequestTwoBatches();
        this.mockMvc.perform(
                put("/inboundorder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("$.batchStock.[0].currentTemperature").value(10.0))
                .andExpect(jsonPath("$.batchStock.[0].minimumTemperature").value(5.0))
                .andExpect(jsonPath("$.batchStock.[1].minimumTemperature").value(5.0))
                .andExpect(jsonPath("$.batchStock.[1].minimumTemperature").value(5.0));

    }

}
