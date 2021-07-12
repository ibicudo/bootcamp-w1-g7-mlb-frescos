package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.unit.order;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.CreateOrderRequestDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.CreateOrderResponseDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.exceptions.NotFoundException;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.Batch;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.Product;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository.BatchRepository;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository.OrderRepository;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository.ProductRepository;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.service.order.OrderServiceImpl;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.unit.factory.BatchFactory;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.unit.factory.CreateOrderRequestDTOFactory;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.unit.factory.ProductFactory;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.util.MockitoExtension;

import org.eclipse.jetty.io.RuntimeIOException;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private BatchRepository batchRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private OrderServiceImpl orderServiceImpl;

    @BeforeEach
    public void setUp() {
        when(orderRepository.save(any())).thenReturn(null);
        when(batchRepository.saveAll(anyList())).thenReturn(null);
        when(batchRepository.save(any())).thenReturn(null);

    }

    @Test
    public void createOrderWithValidValuesTest(){
        CreateOrderRequestDTO mockedCreateOrderRequestDTO = CreateOrderRequestDTOFactory.getOrderWithLegalValues();
        List<Batch> mockedBatchesAlface = BatchFactory.createBatchsListAlface();
        List<Batch> mockedBatchesLeite = BatchFactory.createBatchsListLeite();
        List<Batch> mockedBatchesBatata = BatchFactory.createBatchsListBatata();
        Optional<Product> mockedAlface = Optional.of(ProductFactory.getMockedAlface());
        Optional<Product> mockedLeite = Optional.of(ProductFactory.getMockedLeite());
        Optional<Product> mockedBatata = Optional.of(ProductFactory.getMockedBatata());
        
        when(batchRepository.findByProduct(UUID.fromString("51b3b287-0b78-484c-90c3-606c4bae9401"))).thenReturn(mockedBatchesAlface);
        when(batchRepository.findByProduct(UUID.fromString("1b0d82fa-277f-4f13-a9b7-a6c4c4eec204"))).thenReturn(mockedBatchesLeite);
        when(batchRepository.findByProduct(UUID.fromString("fa0d9b2e-3eac-417e-8ee6-f26037336522"))).thenReturn(mockedBatchesBatata);
        
        when(productRepository.findById(UUID.fromString("51b3b287-0b78-484c-90c3-606c4bae9401"))).thenReturn(mockedAlface);
        when(productRepository.findById(UUID.fromString("1b0d82fa-277f-4f13-a9b7-a6c4c4eec204"))).thenReturn(mockedLeite);
        when(productRepository.findById(UUID.fromString("fa0d9b2e-3eac-417e-8ee6-f26037336522"))).thenReturn(mockedBatata);
        
        CreateOrderResponseDTO orderDTO =  orderServiceImpl.createOrder(mockedCreateOrderRequestDTO);

        assertEquals( 300D, orderDTO.getPrice(), "");
    }

    @Test
    public void createOrderWithNotEnoughProductsTest(){
        CreateOrderRequestDTO mockedCreateOrderRequestDTO = CreateOrderRequestDTOFactory.getOrderNotEnoughProducts();
        List<Batch> mockedBatchesAlface = BatchFactory.createBatchsListAlface();
        Optional<Product> mockedAlface = Optional.of(ProductFactory.getMockedAlface());

        when(batchRepository.findByProduct(UUID.fromString("51b3b287-0b78-484c-90c3-606c4bae9401"))).thenReturn(mockedBatchesAlface);
        when(productRepository.findById(UUID.fromString("51b3b287-0b78-484c-90c3-606c4bae9401"))).thenReturn(mockedAlface);
        
        assertThrows(NotFoundException.class, () -> orderServiceImpl.createOrder(mockedCreateOrderRequestDTO));
    }

    @Test
    public void createOrderWithAProductThatNotExtisTest(){
        CreateOrderRequestDTO mockedCreateOrderRequestDTO = CreateOrderRequestDTOFactory.getOrderProductDoesNotExists();
    
        assertThrows(NoSuchElementException.class, () -> orderServiceImpl.createOrder(mockedCreateOrderRequestDTO));
    }
    
    @Test
    public void createOrderWithNegativeProducts(){
        CreateOrderRequestDTO mockedCreateOrderRequestDTO = CreateOrderRequestDTOFactory.getOrderWithNegativeProductQuantity();
        Optional<Product> mockedAlface = Optional.of(ProductFactory.getMockedAlface());

        when(productRepository.findById(UUID.fromString("51b3b287-0b78-484c-90c3-606c4bae9401"))).thenReturn(mockedAlface);        

        assertThrows(RuntimeIOException.class, () -> orderServiceImpl.createOrder(mockedCreateOrderRequestDTO));
    }

    // listProductsFromOrder
        // orderDoes Exits
        // there is no products to show
        // order does not exists
    
    // modifyOrder
        // order does not exists
        // not enough products
        // setting products to less than 0

}
    
    // {
    //     "order" : [
    //         {
    //             "productId" :, 
    //             "quantity" :
    //         },
    //         {
    //             "productId" :, 
    //             "quantity"  :
    //         },
    //         {
    //             "productId" : ,
    //             "quantity" :
    //         },
    //         {
    //             "productId" : ,
    //             "quantity" :
    //         },
    //     ]
    // }