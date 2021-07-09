package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.service.order;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.CreateOrderRequestDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.CreateOrderResponseDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.ProductOrderDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.exceptions.NotFoundException;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.Batch;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.Order;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.Product;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.ProductOrder;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository.BatchRepository;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository.OrderRepository;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    BatchRepository batchRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public CreateOrderResponseDTO createOrder(CreateOrderRequestDTO createOrderRequestDTO) {
        Order orderToSave = new Order();
        removeFromBatchs(createOrderRequestDTO.getOrder());

        orderToSave.setPrice(orderPricing(createOrderRequestDTO));
        setOrderProducts(createOrderRequestDTO, orderToSave);
        orderRepository.save(orderToSave);

        return new CreateOrderResponseDTO(orderToSave.getPrice());
    }

    @Override
    public List<Product> listProductsFromOrder(UUID orderId) {
        List<Product> order = orderRepository.findById(orderId).get().getProducts();
        
        if (order == null)
            throw new NotFoundException("Order does not exists");
        return order;
    }

    @Override
    public CreateOrderResponseDTO modifyOrder(UUID orderId, CreateOrderRequestDTO createOrderRequestDTO) {
        Order orderToChange = orderRepository.getOne(orderId);
        List<ProductOrderDTO> productsToRemove = new ArrayList<>(); 
        List<ProductOrderDTO> productsToReturn = new ArrayList<>();

        for ( ProductOrderDTO newOrder : createOrderRequestDTO.getOrder()) {
            for (ProductOrder productOrderToChange : orderToChange.getDetailOrder()) {
                // TODO: What hapens if the product he is trying to add does not exists in original order?
                // for now its just ignores we can just sort the two order and then go foreach of then
                // may tranform into a dict
                // maybe just 
                if (newOrder.getProductId() == productOrderToChange.getProduct().getId())
                    if (newOrder.getQuantity() > productOrderToChange.getQuantity())
                        productsToRemove.add(
                            new ProductOrderDTO(newOrder.getProductId(),
                                                newOrder.getQuantity() - productOrderToChange.getQuantity())
                        );
                    else if (newOrder.getQuantity() < productOrderToChange.getQuantity())
                        productsToReturn.add( 
                            new ProductOrderDTO(newOrder.getProductId(),  
                                                productOrderToChange.getQuantity() - newOrder.getQuantity())
                            );
            }
        }
        removeFromBatchs(productsToRemove);
        returnToBatch(productsToReturn);

        orderToChange.setPrice(orderPricing(createOrderRequestDTO));
        setOrderProducts(createOrderRequestDTO, orderToChange);

        orderRepository.save(orderToChange);

        return new CreateOrderResponseDTO();
    }

    private void removeFromBatchs(List<ProductOrderDTO> productsToRemove ){
        List<ProductOrderDTO> productsFalting = new ArrayList<>();
        List<Batch> batches  = new ArrayList<>();
        
        for (ProductOrderDTO  productOrder : productsToRemove) {
            batches = batchRepository.findByProduct(productOrder.getProductId());
            // exception if the batch for a given product does not exists
            // or is less than 1
            batches.sort((Batch b1, Batch b2) -> b2.getDueDate().compareTo(b1.getDueDate()));
            
            for (Batch batch : batches) {
                if (batch.getCurrentQuantity() >= productOrder.getQuantity()){
                    batch.setCurrentQuantity(batch.getCurrentQuantity() - productOrder.getQuantity());
                    productOrder.setQuantity(0);
                }
                else{
                    productOrder.setQuantity(productOrder.getQuantity() - batch.getCurrentQuantity());
                    batch.setCurrentQuantity(0);
                }
                if (productOrder.getQuantity() < 1)
                    break;
            }
            if (productOrder.getQuantity() > 1)
                productsFalting.add(productOrder);
        }

        if (productsFalting.size() > 0)
            // TODO new custom exception
            throw new NotFoundException(productsFalting.toString());
        
        batchRepository.saveAll(batches);
    }

    private void returnToBatch (List<ProductOrderDTO>  producstToReturn){
        for (ProductOrderDTO productOrder : producstToReturn) {
            List<Batch> batches = batchRepository.findByProduct(productOrder.getProductId());
            batches.sort((Batch b1, Batch b2) -> b2.getDueDate().compareTo(b1.getDueDate()));
            batches.get(0).setCurrentQuantity(batches.get(0).getCurrentQuantity() + productOrder.getQuantity());
    
            batchRepository.saveAll(batches);
        }
    }

    private Double orderPricing(CreateOrderRequestDTO createOrderRequestDTO){
        Double result = 0D ;

        for (ProductOrderDTO productOrderDTO : createOrderRequestDTO.getOrder()) {
            Product product = productRepository.findById(productOrderDTO.getProductId()).get();
            result += product.getPrice() * productOrderDTO.getQuantity();
        }
        return result;
    }
    private void setOrderProducts(CreateOrderRequestDTO createOrderRequestDTO, Order order){
        for (ProductOrderDTO productOrderDTO : createOrderRequestDTO.getOrder()) {
            Product product = productRepository.findById(productOrderDTO.getProductId()).get();
            order.getProducts().add(product);
        }
    }
}
