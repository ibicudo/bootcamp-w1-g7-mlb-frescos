package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.service.product;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.ProductWarehouseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public ProductWarehouseDTO getProductsInAllWarehouses(UUID productId) {
        return null;
    }
}
