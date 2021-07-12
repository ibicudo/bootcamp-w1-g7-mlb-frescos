
package com.mercadolibre.ingrid_bicudo.service.inboundorder;

import com.mercadolibre.ingrid_bicudo.dtos.BatchStockDTO;
import com.mercadolibre.ingrid_bicudo.dtos.CreateInboundOrderDTO;
import com.mercadolibre.ingrid_bicudo.dtos.ProductBatchStockDTO;
import com.mercadolibre.ingrid_bicudo.dtos.UpdateInboundOrderDTO;
import com.mercadolibre.ingrid_bicudo.model.Account;

import java.util.UUID;

public interface InboundOrderService {

    BatchStockDTO createInboundOrder(CreateInboundOrderDTO createInboundOrderDTO, Account account);
    BatchStockDTO updateInboundOrder(UpdateInboundOrderDTO inboundOrderDTO, Account account);
    ProductBatchStockDTO listProductBatchStock(UUID productId, Account account, String sortParam);
}
