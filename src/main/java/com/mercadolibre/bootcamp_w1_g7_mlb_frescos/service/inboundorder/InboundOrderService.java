package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.service.inboundorder;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.BatchStockDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.CreateInboundOrderDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.ProductBatchStockDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.UpdateInboundOrderDTO;

import java.util.UUID;

public interface InboundOrderService {

    BatchStockDTO createInboundOrder(CreateInboundOrderDTO createInboundOrderDTO);
    BatchStockDTO updateInboundOrder(UpdateInboundOrderDTO inboundOrderDTO);
    ProductBatchStockDTO listProductBatchStock(UUID productId, UUID supervisorId, String sortParam);
}
