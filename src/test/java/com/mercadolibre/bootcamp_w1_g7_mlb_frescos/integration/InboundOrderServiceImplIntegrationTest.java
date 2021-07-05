package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.integration;


import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.util.TestUniUtilsGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class InboundOrderServiceImplIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createInboundOrderWithOneBatch() throws Exception {
        String request = TestUniUtilsGenerator.createRequestOneBatch();
        this.mockMvc.perform(
                post("/inboundorder")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(request))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].currentTemperature").value(10.0))
                .andExpect(jsonPath("$.[0].initialQuatity").value(500))
                .andExpect(jsonPath("$.[0].CurrentQuantity").value(500));

    }

    @Test
    void createInboundOrderWithTwoBatch() throws Exception {
        String request = TestUniUtilsGenerator.createRequestTwoBatches();
        this.mockMvc.perform(
                post("/inboundorder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].currentTemperature").value(10.0))
                .andExpect(jsonPath("$.[1].currentTemperature").value(12.0));

    }

    @Test
    void createInboundOrderWithThreeBatch() throws Exception {
        String request =  TestUniUtilsGenerator.createRequestThreeBatches();
        this.mockMvc.perform(
                post("/inboundorder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].currentTemperature").value(10.0))
                .andExpect(jsonPath("$.[1].currentTemperature").value(12.0))
                .andExpect(jsonPath("$.[2].currentTemperature").value(13.0));
    }

}
