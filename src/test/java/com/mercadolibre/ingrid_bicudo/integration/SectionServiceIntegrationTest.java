package com.mercadolibre.ingrid_bicudo.integration;

import com.mercadolibre.ingrid_bicudo.model.Section;
import com.mercadolibre.ingrid_bicudo.model.Warehouse;
import com.mercadolibre.ingrid_bicudo.repository.*;
import com.mercadolibre.ingrid_bicudo.util.TestUtilsGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SectionServiceIntegrationTest extends IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private SupervisorRepository supervisorRepository;

    @Autowired
    private SectionRepository sectionRepository;

    private String token;
    private Section section;
    private Warehouse warehouse;

    @BeforeAll
    void setup() {
        token = TestUtilsGenerator.createToken();
        warehouse = TestUtilsGenerator.createWarehouse();
        section = new Section();
        section.setCode("OSAF001");
        section.setCategory("FS");
        section.setCapacity(100);
        section.setWarehouse(warehouse);
    }


    @Test
    void updateInboundOrderWithOneBatch() throws Exception {
            sectionRepository.save(section);

            String request = TestUtilsGenerator.updateSection();
            this.mockMvc.perform(
                    put("/api/v1/fresh-products/section")
                            .contentType(MediaType.APPLICATION_JSON)
                            .header("Authorization", token)
                            .content(request))
                    .andDo(print()).andExpect(status().isOk());

    }
}
