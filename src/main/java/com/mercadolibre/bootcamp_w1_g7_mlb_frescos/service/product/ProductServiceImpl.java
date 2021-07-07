package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.service.product;

import java.util.List;
import java.util.stream.Collectors;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.ProductListResponseDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.exceptions.NotFoundException;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.Product;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    
    @Override
    public ProductListResponseDTO listProducts(String filter) {
        List<Product> products = productRepository.findAll();
        if (filter == null ){
            if (products.isEmpty())
                throw new NotFoundException("there are no products");
            return new ProductListResponseDTO(products);
        }
        return new ProductListResponseDTO(products.stream()
                .filter(product -> (!product.getCategory().equals(filter)))
                .collect(Collectors.toList()));
    }
    
}
