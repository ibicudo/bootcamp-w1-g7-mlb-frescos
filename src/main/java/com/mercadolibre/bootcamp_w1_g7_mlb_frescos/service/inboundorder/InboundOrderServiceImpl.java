package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.service.inboundorder;

import com.google.common.collect.Sets;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.BatchDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.BatchStockDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.InboundOrderDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.exceptions.BadRequestException;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.Batch;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.InboundOrder;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.Product;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.Section;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository.InboundOrderRepository;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository.ProductRepository;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository.SectionRepository;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository.SupervisorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InboundOrderServiceImpl implements InboundOrderService {

//    TODO E que o representante pertence ao armazém

//    QUANDO o representante entra no lote
//    ENTÃO o registro de compra é criado
//    E o lote é atribuído a um setor
//    E o representante está associado ao registro de compra


    private final ProductRepository productRepository;

    private final SupervisorRepository supervisorRepository;

    private final SectionRepository sectionRepository;

    private final InboundOrderRepository inboundOrderRepository;

    private final ModelMapper modelMapper;


    public InboundOrderServiceImpl(ProductRepository productRepository, SupervisorRepository supervisorRepository,
                                   SectionRepository sectionRepository, InboundOrderRepository inboundOrderRepository,
                                   ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.supervisorRepository = supervisorRepository;
        this.sectionRepository = sectionRepository;
        this.inboundOrderRepository = inboundOrderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public BatchStockDTO createInboundOrder(InboundOrderDTO inboundOrderDTO) {
        Section section = sectionRepository.findById(inboundOrderDTO.getSection().getSectionCode())
                .orElseThrow(() -> new BadRequestException("Section not found"));


        checkWarehouse(section.getWarehouse().getCode(), inboundOrderDTO.getSection().getWarehouseCode());
        //TODO supervisor check
        checkSection(section.getSectionCode(), inboundOrderDTO.getSection().getSectionCode());
        List<Product> products = getExistingProducts(inboundOrderDTO.getBatchStock());
        checkProducts(inboundOrderDTO.getBatchStock(), products);
        checkProductsCategory(products, section.getCategory());
        checkSectionCapacity(section, products.size());

        // TODO: Adicionar Supervisor ao InboundOrder

        InboundOrder inboundOrder = modelMapper.map(inboundOrderDTO, InboundOrder.class);

//        InboundOrder inboundOrder = modelMapper
//                .typeMap(InboundOrderDTO.class, InboundOrder.class)
//                .addMapping(mapper -> mapper.getSection().getSectionCode(),
//                        (destination, value) -> destination.getSection().setCode((String) value))
//                .map(inboundOrderDTO);

        inboundOrderRepository.save(inboundOrder);

        return new BatchStockDTO(inboundOrderDTO.getBatchStock());
    }

    private void checkWarehouse(String expected, String received) {
        if (!expected.equals(received)) {
            throw new BadRequestException("Warehouse and Section do not match");
        }
    }

    private void checkSection(String expected, String received) {
        if (!expected.equals(received)) {
            throw new BadRequestException("Section and Warehouse do not match");
        }
    }

    private List<Product> getExistingProducts(List<BatchDTO> batches) {
        List<UUID> listProductsId = batches.stream()
                .map(BatchDTO::getProductId).collect(Collectors.toList());

        return productRepository.findAllById(listProductsId);
    }

    private void checkProducts(List<BatchDTO> batches, List<Product> products) {

        if (batches.size() == products.size()) {
            return;
        }

        Set<UUID> productIdsInBatch = batches.stream().map(BatchDTO::getProductId).collect(Collectors.toSet());
        Set<UUID> retrievedProductIds = products.stream()
                .map(Product::getId).collect(Collectors.toSet());

        Set<UUID> missingProducts = Sets.difference(productIdsInBatch, retrievedProductIds);

        throw new BadRequestException("Products with ids " + missingProducts + " are missing in the database");
    }

    private void checkProductsCategory(List<Product> products, String category) {
        List<String> productsWithWrongCategory = products.stream()
                .map(Product::getCategory)
                .filter(productCategory -> (!productCategory.equals(category)))
                .collect(Collectors.toList());

        if (!productsWithWrongCategory.isEmpty()) {
            throw new BadRequestException("Products with ids " + productsWithWrongCategory.toString() + " can not be stored in this section");
        }
    }

    private void checkSectionCapacity(Section section, int neededSpace)  {
        Integer usedSpace = section.getInboundOrder().stream()
                .map(InboundOrder::getBatchStock).map(List::size).reduce(0, Integer::sum);

        if(usedSpace + neededSpace > section.getCapacity()) {
            throw new BadRequestException("The section can not contain this inboundOrder");
        }
    }

}
