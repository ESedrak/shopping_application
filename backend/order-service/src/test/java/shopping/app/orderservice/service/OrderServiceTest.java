package shopping.app.orderservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import shopping.app.orderservice.client.InventoryClient;
import shopping.app.orderservice.dto.OrderRequest;
import shopping.app.orderservice.event.OrderPlacedEvent;
import shopping.app.orderservice.model.Order;
import shopping.app.orderservice.repository.OrderRepository;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private InventoryClient inventoryClient;
    @Mock
    private KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    @InjectMocks
    private OrderService orderService;

    OrderRequest orderRequest;
    Order mockOrder;

    @BeforeEach
    void setUp() {
        orderRequest = new OrderRequest(null, null, "testSku", new BigDecimal("100.00"), 1, new OrderRequest.UserDetails("test@example.com", "Test User", "1234567890"));
        mockOrder = new Order(1L, "123456789", "testSku", new BigDecimal("100.00"), 1);
        lenient().when(orderRepository.save(any(Order.class))).thenReturn(mockOrder);
    }

    @Test
    void orderIsSaved_WhenProductInStock() {
        when(inventoryClient.isInStock(anyString(), anyInt())).thenReturn(true);
        orderService.placeOrder(orderRequest);

        ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);
        verify(orderRepository, times(1)).save(orderCaptor.capture());
        Order savedOrder = orderCaptor.getValue();
        assertEquals("testSku", savedOrder.getSkuCode());
        assertEquals(new BigDecimal("100.00"), savedOrder.getPrice());
        assertEquals(1, savedOrder.getQuantity());
    }

    @Test
    void orderPlacedEventIsPublished_WhenProductInStock() {
        when(inventoryClient.isInStock(anyString(), anyInt())).thenReturn(true);
        orderService.placeOrder(orderRequest);

        ArgumentCaptor<OrderPlacedEvent> eventCaptor = ArgumentCaptor.forClass(OrderPlacedEvent.class);
        verify(kafkaTemplate, times(1)).send(eq("order-placed"), eventCaptor.capture());
        OrderPlacedEvent publishedEvent = eventCaptor.getValue();
        assertEquals(mockOrder.getOrderNumber(), publishedEvent.getOrderNumber());
        assertEquals(orderRequest.userDetails().email(), publishedEvent.getEmail());
    }

    @Test
    void whenProductNotInStock_ThrowsRuntimeException() {
        when(inventoryClient.isInStock(anyString(), anyInt())).thenReturn(false);
        Exception exception = assertThrows(RuntimeException.class, () -> orderService.placeOrder(orderRequest));
        assertEquals("Product with skuCode +testSku is not in stock", exception.getMessage());
    }
}