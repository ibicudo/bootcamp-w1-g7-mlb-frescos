package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.service.inboundorder;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.BatchStockDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.InboundOrderDTO;

public interface InboundOrderService {

    BatchStockDTO createInboundOrder(InboundOrderDTO inboundOrderDTO);
}
