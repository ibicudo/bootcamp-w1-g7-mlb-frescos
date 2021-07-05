package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.integration;


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
        String request = "{\"orderNumber\": 1,\"orderDate\": \"2021-07-01\", \"section\": {\"sectionCode\": \"CAJF001\" , \"warehouseCode\": \"CAJF\"}, \"batchStock\": [" +
                getBatchStock(1, "51b3b287-0b78-484c-90c3-606c4bae9401", 10.0, 5.0,
                        500, 500, "2021-06-10", "2021-06-03 00:00:00", "2021-08-15") + "]}";
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
        String request = "{\"orderNumber\": 1,\"orderDate\": \"2021-07-01\", \"section\": {\"sectionCode\": \"CAJF001\" , \"warehouseCode\": \"CAJF\"}, \"batchStock\": [" +
                getBatchStock(1, "51b3b287-0b78-484c-90c3-606c4bae9401", 10.0, 5.0,
                        500, 500, "2021-06-10", "2021-06-03 00:00:00", "2021-08-15") + "," +
                getBatchStock(2, "fa0d9b2e-3eac-417e-8ee6-f26037336522", 12.0, 5.0,
                        500, 500, "2021-06-10", "2021-06-03 00:00:00", "2021-08-15") + "]}";
        this.mockMvc.perform(
                post("/inboundorder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].currentTemperature").value(10.0))
                .andExpect(jsonPath("$.[1].currentTemperature").value(12.0));

    }

    @Test
    void createInboundThreeWithTwoBatch() throws Exception {
        String request = "{\"orderNumber\": 1,\"orderDate\": \"2021-07-01\", \"section\": {\"sectionCode\": \"CAJF001\" , \"warehouseCode\": \"CAJF\"}, \"batchStock\": [" +
                getBatchStock(1, "51b3b287-0b78-484c-90c3-606c4bae9401", 10.0, 5.0,
                        500, 500, "2021-06-10", "2021-06-03 00:00:00", "2021-08-15") + "," +
                getBatchStock(2, "fa0d9b2e-3eac-417e-8ee6-f26037336522", 12.0, 5.0,
                        500, 500, "2021-06-10", "2021-06-03 00:00:00", "2021-08-15") + "," +
                getBatchStock(3, "fa0d9b2e-4eis-412d-8uu5-j87870989888", 13.0, 5.0,
                        400, 400, "2021-06-10", "2021-06-03 00:00:00", "2021-08-15") + "]}";
        this.mockMvc.perform(
                post("/inboundorder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].currentTemperature").value(10.0))
                .andExpect(jsonPath("$.[1].currentTemperature").value(12.0))
                .andExpect(jsonPath("$.[2].currentTemperature").value(13.0));
    }


    //TODO verificar se os types do das datas estao corretas
    private String getBatchStock(Integer batchNumber, String productId, Double currentTemperature, Double minimumTemperature, Integer initialQuantity, Integer currentQuantity, String manufacturingDate, String manufacturingTime, String dueDate) {
        return "{\"batchNumber\": "+batchNumber+", \"productId\": \""+productId+"\", \"currentTemperature\": "+currentTemperature+", " +
                "\"minimumTemperature\": "+minimumTemperature+", \"initialQuantity\":"+initialQuantity+", \"currentQuantity\": "+currentQuantity+", " +
                "\"manufacturingDate\": \""+manufacturingDate+"\", \"manufacturingTime\": \""+manufacturingTime+"\", \"dueDate\": \""+dueDate+"\"}";
    }



}
