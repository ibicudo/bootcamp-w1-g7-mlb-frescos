package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.integration;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.BatchStockDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.CreateInboundOrderDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.service.inboundorder.InboundOrderService;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.util.TestUniUtilsGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class InboundOrderServiceImplIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

//    @MockBean
//    InboundOrderService service;

    @Test
    void createInboundOrderWithOneBatch() throws Exception {
        String request = TestUniUtilsGenerator.createRequestOneBatch();
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
        String request = TestUniUtilsGenerator.createRequestTwoBatches();
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
    void createInboundOrderWithThreeBatch() throws Exception {
        String request =  TestUniUtilsGenerator.createRequestThreeBatches();
        this.mockMvc.perform(
                post("/inboundorder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("$.batchStock.[0].currentTemperature").value(10.0))
                .andExpect(jsonPath("$.batchStock.[0].minimumTemperature").value(5.0))
                .andExpect(jsonPath("$.batchStock.[1].currentTemperature").value(10.0))
                .andExpect(jsonPath("$.batchStock.[1].minimumTemperature").value(5.0))
                .andExpect(jsonPath("$.batchStock.[2].currentTemperature").value(10.0))
                .andExpect(jsonPath("$.batchStock.[2].minimumTemperature").value(5.0));
    }


    @Test
    void updateInboundOrderWithOneBatch() throws Exception {
        //CreateInboundOrderDTO createInboundOrderDTO= TestUniUtilsGenerator.getInboundOrderDto();
        //BatchStockDTO batchStockDTO = new BatchStockDTO();
        //when(service.updateInboundOrder(any())).thenReturn(batchStockDTO);

        String request = TestUniUtilsGenerator.updateRequestOneBatch();
        this.mockMvc.perform(
                put("/inboundorder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("$.batchStock.[0].currentTemperature").value(10.0));
//                .andExpect(jsonPath("$.batchStock.[0].minimumTemperature").value(5.0))
//                .andExpect(jsonPath("$.batchStock.[0].initialQuantity").value(500));
    }

    @Test
    void updateInboundOrderWithTwoBatch() throws Exception {
        String request = TestUniUtilsGenerator.createRequestTwoBatches();
        this.mockMvc.perform(
                put("/inboundorder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("$.batchStock.[0].currentTemperature").value(10.0))
                .andExpect(jsonPath("$.batchStock.[0].minimumTemperature").value(5.0))
                .andExpect(jsonPath("$.batchStock.[1].currentTemperature").value(10.0))
                .andExpect(jsonPath("$.batchStock.[1].minimumTemperature").value(5.0));

    }

    @Test
    void updateInboundOrderWithThreeBatch() throws Exception {
        String request =  TestUniUtilsGenerator.createRequestThreeBatches();
        this.mockMvc.perform(
                put("/inboundorder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("$.batchStock.[0].currentTemperature").value(10.0))
                .andExpect(jsonPath("$.batchStock.[0].minimumTemperature").value(5.0))
                .andExpect(jsonPath("$.batchStock.[1].currentTemperature").value(10.0))
                .andExpect(jsonPath("$.batchStock.[1].minimumTemperature").value(5.0))
                .andExpect(jsonPath("$.batchStock.[2].currentTemperature").value(10.0))
                .andExpect(jsonPath("$.batchStock.[2].minimumTemperature").value(5.0));
    }

}
