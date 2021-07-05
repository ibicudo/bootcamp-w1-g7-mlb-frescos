package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.util;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.BatchDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.CreateInboundOrderDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.InboundOrderDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.SectionDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class TestUniUtilsGenerator {

    public static InboundOrderDTO getInboundOrderDto (){
        InboundOrderDTO inboundOrderDTO = new InboundOrderDTO();

        SectionDTO sectionDTO = new SectionDTO();
        sectionDTO.setSectionCode("CAJF001");
        sectionDTO.setWarehouseCode("CAJF");

        Product product = new Product();
        product.setId(UUID.fromString("51b3b287-0b78-484c-90c3-606c4bae9401"));
        product.setName("Alface");
        product.setCategory("FS");

        List<BatchDTO> batches = new ArrayList<>();
        BatchDTO batchDTO1 = new BatchDTO();
        batchDTO1.setBatchNumber(1);
        batchDTO1.setProductId(product.getId());
        batchDTO1.setCurrentTemperature(10.0);
        batchDTO1.setMinimumTemperature(5.0);
        batchDTO1.setInitialQuantity(500);
        batchDTO1.setCurrentQuantity(500);
        batchDTO1.setManufacturingDate(LocalDate.of(2021, 06, 10));
        batchDTO1.setManufacturingTime(LocalDateTime.of(2021, 06, 03, 00, 00, 00));
        batchDTO1.setDueDate(LocalDate.of(2021, 8, 15));

        batches.add(batchDTO1);

        inboundOrderDTO.setOrderNumber(1);
        inboundOrderDTO.setOrderDate(LocalDate.of(2021, 07, 01));
        inboundOrderDTO.setSection(sectionDTO);
        inboundOrderDTO.setBatchStock(batches);

        return inboundOrderDTO;
    }

    public static Section createSection (){
        Batch batch = new Batch();
        List<Batch> batches = new ArrayList<>();
        batches.add(batch);
        InboundOrder inboundOrder = new InboundOrder();
        inboundOrder.setBatchStock(batches);
        Set<InboundOrder> inboundOrderSet = new HashSet<>();
        inboundOrderSet.add(inboundOrder);

        Section section = new Section();
        section.setSectionCode("CAJF001");
        section.setCategory("FS");
        section.setCapacity(500);
        section.setWarehouse(new Warehouse("CAJF", "Fullfillment Cajamar"));
        section.setInboundOrder(inboundOrderSet);

        return section;
    }

    public static List<Product> createListProducts(){
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setId(UUID.fromString("51b3b287-0b78-484c-90c3-606c4bae9401"));
        product.setName("Alface");
        product.setCategory("FS");
        products.add(product);

        return products;
    }

}
