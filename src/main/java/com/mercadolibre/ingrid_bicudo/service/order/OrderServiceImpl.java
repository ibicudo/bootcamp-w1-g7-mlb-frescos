package com.mercadolibre.ingrid_bicudo.service.order;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.mercadolibre.ingrid_bicudo.dtos.CreateOrderRequestDTO;
import com.mercadolibre.ingrid_bicudo.dtos.CreateOrderResponseDTO;
import com.mercadolibre.ingrid_bicudo.dtos.ProductOrderDTO;
import com.mercadolibre.ingrid_bicudo.exceptions.NotFoundException;
import com.mercadolibre.ingrid_bicudo.model.Batch;
import com.mercadolibre.ingrid_bicudo.model.Order;
import com.mercadolibre.ingrid_bicudo.model.Product;
import com.mercadolibre.ingrid_bicudo.model.ProductOrder;
import com.mercadolibre.ingrid_bicudo.repository.BatchRepository;
import com.mercadolibre.ingrid_bicudo.repository.OrderRepository;
import com.mercadolibre.ingrid_bicudo.repository.ProductRepository;

import org.eclipse.jetty.io.RuntimeIOException;
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
    public CreateOrderResponseDTO createOrder(CreateOrderRequestDTO createOrderRequestDTO)  throws NotFoundException{
        Order orderToSave = new Order();
        setOrderProducts(createOrderRequestDTO, orderToSave);
        removeFromBatchs(createOrderRequestDTO.getOrder());

        orderRepository.save(orderToSave);

        return new CreateOrderResponseDTO(orderToSave.getPrice());
    }

    @Override
    public List<ProductOrder> listProductsFromOrder(UUID orderId) {
        List<ProductOrder> order = orderRepository.findById(orderId).get().getDetailOrder();
        return order;
    }

    @Override
    public CreateOrderResponseDTO modifyOrder(UUID orderId, CreateOrderRequestDTO createOrderRequestDTO) {
        Order orderToChange = orderRepository.getOne(orderId);
        
        List<ProductOrderDTO> productsToReturn = new ArrayList<>();

        for (ProductOrder productOrder : orderToChange.getDetailOrder()){
            productsToReturn.add(new ProductOrderDTO(productOrder.getProduct().getId(), productOrder.getQuantity()));
        }
        returnToBatch(productsToReturn);
        setOrderProducts(createOrderRequestDTO, orderToChange);
        removeFromBatchs(createOrderRequestDTO.getOrder());
            
        orderRepository.save(orderToChange);

        return new CreateOrderResponseDTO(orderToChange.getPrice());
    }

    private void removeFromBatchs(List<ProductOrderDTO> productsToRemove ){
        List<ProductOrderDTO> productsFalting = new ArrayList<>();
        List<Batch> batches  = new ArrayList<>();
        
        for (ProductOrderDTO  productOrder : productsToRemove) {
            batches = batchRepository.findByProduct(productOrder.getProductId());
            batches.sort((Batch b1, Batch b2) -> b1.getDueDate().compareTo(b2.getDueDate()));
            
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
            throw new NotFoundException(productsFalting.toString());
        
        batchRepository.saveAll(batches);
    }

    private void returnToBatch (List<ProductOrderDTO>  producstToReturn){
        for (ProductOrderDTO productOrder : producstToReturn) {
            List<Batch> batches = batchRepository.findByProduct(productOrder.getProductId());
            batches.sort((Batch b1, Batch b2) -> b1.getDueDate().compareTo(b2.getDueDate()));
            batches.get(0).setCurrentQuantity(batches.get(0).getCurrentQuantity() + productOrder.getQuantity());
    
            batchRepository.saveAll(batches);
        }
    }

    private void setOrderProducts(CreateOrderRequestDTO createOrderRequestDTO, Order order){
        order.setPrice(0D);
        for (ProductOrderDTO productOrderDTO : createOrderRequestDTO.getOrder()) {
            Product product = productRepository.findById(productOrderDTO.getProductId()).get();
            ProductOrder productOrder = new ProductOrder();
            
            if (product.getPrice() <= 0 || productOrderDTO.getQuantity() <= 0)
                throw new RuntimeIOException("Price or Quantity of product:" + product.getName() + "cant be less than or 0");

            order.setPrice( order.getPrice() + product.getPrice() * productOrderDTO.getQuantity() );

            productOrder.setOrder(order);
            productOrder.setProduct(product);
            productOrder.setQuantity(productOrderDTO.getQuantity());

            order.getDetailOrder().add(productOrder);
        }

    }
}
