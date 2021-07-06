package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.*;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.*;
import net.bytebuddy.implementation.bind.annotation.Super;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class TestUniUtilsGenerator {

    public static CreateInboundOrderDTO getInboundOrderDto (){
        CreateInboundOrderDTO createInboundOrderDTO = new CreateInboundOrderDTO();
        InboundOrderWithoutOrderNumberDTO inboundOrderWithoutOrderNumberDTO = new InboundOrderWithoutOrderNumberDTO();

        SectionDTO sectionDTO = new SectionDTO();
        sectionDTO.setSectionCode("OSAF001");
        sectionDTO.setWarehouseCode("OSAF");

        Product product = new Product();
        product.setId(UUID.fromString("51b3b287-0b78-484c-90c3-606c4bae9401"));
        product.setName("Alface");
        product.setCategory("FS");

        List<BatchWithoutNumberDTO> batches = new ArrayList<>();
        BatchWithoutNumberDTO batchDTO1 = new BatchWithoutNumberDTO();
        batchDTO1.setProductId(product.getId());
        batchDTO1.setCurrentTemperature(10.0);
        batchDTO1.setMinimumTemperature(5.0);
        batchDTO1.setInitialQuantity(500);
        batchDTO1.setCurrentQuantity(500);
        batchDTO1.setManufacturingDate(LocalDate.of(2021, 06, 10));
        batchDTO1.setManufacturingTime(LocalDateTime.of(2021, 06, 03, 00, 00, 00));
        batchDTO1.setDueDate(LocalDate.of(2021, 8, 15));

        batches.add(batchDTO1);

        inboundOrderWithoutOrderNumberDTO.setOrderDate(LocalDate.of(2021, 07, 01));
        inboundOrderWithoutOrderNumberDTO.setSection(sectionDTO);
        inboundOrderWithoutOrderNumberDTO.setBatchStock(batches);
        createInboundOrderDTO.setInboundOrder(inboundOrderWithoutOrderNumberDTO);

        return createInboundOrderDTO;
    }

    public static InboundOrderDTO createInboundOrderDTO(){
        InboundOrderDTO inboundOrderDTO = new InboundOrderDTO();

        SectionDTO sectionDTO = new SectionDTO();
        sectionDTO.setSectionCode("OSAF001");
        sectionDTO.setWarehouseCode("OSAF");

        Product product = new Product();
        product.setId(UUID.fromString("51b3b287-0b78-484c-90c3-606c4bae9401"));
        product.setName("Alface");
        product.setCategory("FS");

        List<BatchDTO> batches = new ArrayList<>();
        BatchDTO batchDTO1 = new BatchDTO();
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
        Set<Batch> batches = new HashSet<>();
        batches.add(batch);
        InboundOrder inboundOrder = new InboundOrder();
        inboundOrder.setBatchStock(batches);
        Set<InboundOrder> inboundOrderSet = new HashSet<>();
        inboundOrderSet.add(inboundOrder);

        Section section = new Section();
        section.setCode("OSAF001");
        section.setCategory("FS");
        section.setCapacity(500);
        section.setWarehouse(new Warehouse("OSAF", "Fullfillment Osasco"));
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

    public static Supervisor createSupervisor(){
        Supervisor supervisor = new Supervisor();
        Warehouse warehouse = new Warehouse();
        warehouse.setCode("OSAF");
        warehouse.setName("Fullfillment Osasco");
        supervisor.setId(UUID.fromString("cdd7bfff-1eeb-4fe8-b3ed-7fb2c0304020"));
        supervisor.setName("Maria");
        supervisor.setWarehouse(warehouse);

        return supervisor;
    }

    public static InboundOrder createInboundOrder(){
        InboundOrder inboundOrder = new InboundOrder();
        inboundOrder.setOrderNumber(1);
        inboundOrder.setSection(createSection());
        inboundOrder.setSupervisor(createSupervisor());
        inboundOrder.setOrderDate(LocalDate.of(2021, 07, 05));
        Set<Batch> batchStock = createBatchStock();
        batchStock.forEach(batch -> batch.setInboundOrder(inboundOrder));
        inboundOrder.setBatchStock(batchStock);

        return inboundOrder;
    }

    public static Set<Batch> createBatchStock(){
        Set<Batch> batches = new HashSet<>();
        Batch batch = new Batch();
        Product product = new Product();
        product.setId(UUID.fromString("51b3b287-0b78-484c-90c3-606c4bae9401"));
        batch.setProduct(product);
        batch.setCurrentTemperature(10.0);
        batch.setMinimumTemperature(5.0);
        batch.setInitialQuantity(500);
        batch.setCurrentQuantity(500);
        batch.setManufacturingDate(LocalDate.of(2021, 06, 10));
        batch.setManufacturingTime(LocalDateTime.of(2021, 06, 10, 20, 00, 00));
        batch.setDueDate(LocalDate.of(2021, 8, 15));
        batch.setBatchNumber(1);

        batches.add(batch);

        return batches;
    }


    public static String createRequestOneBatch(){
        String request = "{\"orderNumber\": 1,\"orderDate\": \"2021-07-01\", \"section\": {\"sectionCode\": \"CAJF001\" , \"warehouseCode\": \"CAJF\"}, \"batchStock\": [" +
                getBatchStock(1, "51b3b287-0b78-484c-90c3-606c4bae9401", 10.0, 5.0,
                        500, 500, "2021-06-10", "2021-06-03 00:00:00", "2021-08-15") + "]}";

        return request;
    }

    public static String createRequestTwoBatches(){
        String request = "{\"orderNumber\": 1,\"orderDate\": \"2021-07-01\", \"section\": {\"sectionCode\": \"CAJF001\" , \"warehouseCode\": \"CAJF\"}, \"batchStock\": [" +
                getBatchStock(1, "51b3b287-0b78-484c-90c3-606c4bae9401", 10.0, 5.0,
                        500, 500, "2021-06-10", "2021-06-03 00:00:00", "2021-08-15") + "," +
                getBatchStock(2, "fa0d9b2e-3eac-417e-8ee6-f26037336522", 12.0, 5.0,
                        500, 500, "2021-06-10", "2021-06-03 00:00:00", "2021-08-15") + "]}";
        return request;
    }

    public static String createRequestThreeBatches(){
        String request = "{\"orderNumber\": 1,\"orderDate\": \"2021-07-01\", \"section\": {\"sectionCode\": \"CAJF001\" , \"warehouseCode\": \"CAJF\"}, \"batchStock\": [" +
                getBatchStock(1, "51b3b287-0b78-484c-90c3-606c4bae9401", 10.0, 5.0,
                        500, 500, "2021-06-10", "2021-06-03 00:00:00", "2021-08-15") + "," +
                getBatchStock(2, "fa0d9b2e-3eac-417e-8ee6-f26037336522", 12.0, 5.0,
                        500, 500, "2021-06-10", "2021-06-03 00:00:00", "2021-08-15") + "," +
                getBatchStock(3, "fa0d9b2e-4eis-412d-8uu5-j87870989888", 13.0, 5.0,
                        400, 400, "2021-06-10", "2021-06-03 00:00:00", "2021-08-15") + "]}";
        return request;
    }

    private static String getBatchStock(Integer batchNumber, String productId, Double currentTemperature, Double minimumTemperature, Integer initialQuantity, Integer currentQuantity, String manufacturingDate, String manufacturingTime, String dueDate) {
        return "{\"batchNumber\": "+batchNumber+", \"productId\": \""+productId+"\", \"currentTemperature\": "+currentTemperature+", " +
                "\"minimumTemperature\": "+minimumTemperature+", \"initialQuantity\":"+initialQuantity+", \"currentQuantity\": "+currentQuantity+", " +
                "\"manufacturingDate\": \""+manufacturingDate+"\", \"manufacturingTime\": \""+manufacturingTime+"\", \"dueDate\": \""+dueDate+"\"}";
    }

}
