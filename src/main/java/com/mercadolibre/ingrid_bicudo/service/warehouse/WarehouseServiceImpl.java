package com.mercadolibre.ingrid_bicudo.service.warehouse;

import com.mercadolibre.ingrid_bicudo.dtos.ProductWarehouseDTO;
import com.mercadolibre.ingrid_bicudo.dtos.WarehouseQuantityDTO;
import com.mercadolibre.ingrid_bicudo.exceptions.BadRequestException;
import com.mercadolibre.ingrid_bicudo.exceptions.NotFoundException;
import com.mercadolibre.ingrid_bicudo.model.Batch;
import com.mercadolibre.ingrid_bicudo.model.Product;
import com.mercadolibre.ingrid_bicudo.model.Warehouse;
import com.mercadolibre.ingrid_bicudo.repository.BatchRepository;
import com.mercadolibre.ingrid_bicudo.repository.ProductRepository;
import com.mercadolibre.ingrid_bicudo.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final ProductRepository productRepository;
    private final BatchRepository batchRepository;
    private final WarehouseRepository warehouseRepository;

    public WarehouseServiceImpl(ProductRepository productRepository, BatchRepository batchRepository, WarehouseRepository warehouseRepository) {
        this.productRepository = productRepository;
        this.batchRepository = batchRepository;
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public ProductWarehouseDTO getProductsInAllWarehouses(UUID productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BadRequestException("Product not found"));

        List<String> warehouseCodes = warehouseRepository.findAll().stream().map(Warehouse::getCode).collect(Collectors.toList());

        ProductWarehouseDTO productWarehouseDTO = new ProductWarehouseDTO();
        productWarehouseDTO.setProductId(product.getId().toString());
        List<WarehouseQuantityDTO> warehousesQuantity = new ArrayList<>();

        warehouseCodes.forEach(code -> {
            WarehouseQuantityDTO warehouseQuantityDTO = new WarehouseQuantityDTO(code,
                    batchRepository.findBatchesByProductAndWarehouse(product.getId(), code).stream().
                    map(Batch::getCurrentQuantity).reduce(0, Integer::sum).toString());
            warehousesQuantity.add(warehouseQuantityDTO);
        });

        checkWarehouseDoesNotContainsProduct(warehousesQuantity);

        warehousesQuantity.sort(Comparator.comparing(WarehouseQuantityDTO::getTotalQuantity).reversed());
        productWarehouseDTO.setWarehouses(warehousesQuantity);

        return productWarehouseDTO;
    }

    private void checkWarehouseDoesNotContainsProduct(List<WarehouseQuantityDTO> warehousesQuantity) {

        List<WarehouseQuantityDTO> warehouseQuantityNotZero = warehousesQuantity.stream().filter(warehouse ->
            !warehouse.getTotalQuantity().equals("0")
        ).collect(Collectors.toList());

        if(warehouseQuantityNotZero.isEmpty()){
            throw new NotFoundException("There are no quantity of this product available in warehouses");
        }
    }
}
