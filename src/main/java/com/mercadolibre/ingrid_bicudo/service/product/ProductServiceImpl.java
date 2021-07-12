package com.mercadolibre.ingrid_bicudo.service.product;

import java.util.List;
import java.util.stream.Collectors;

import com.mercadolibre.ingrid_bicudo.dtos.ProductListResponseDTO;
import com.mercadolibre.ingrid_bicudo.exceptions.NotFoundException;
import com.mercadolibre.ingrid_bicudo.model.Product;
import com.mercadolibre.ingrid_bicudo.repository.ProductRepository;

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
