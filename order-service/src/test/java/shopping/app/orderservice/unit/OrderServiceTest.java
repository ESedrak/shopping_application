package shopping.app.orderservice.unit;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import shopping.app.orderservice.dto.OrderRequest;
import shopping.app.orderservice.model.Order;
import shopping.app.orderservice.repository.OrderRepository;
import shopping.app.orderservice.service.OrderService;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void testPlaceOrder() {
        // Given
        OrderRequest orderRequest = new OrderRequest(1L, "12345", "iphone_15", new BigDecimal("1000"), 1);
        Order order = new Order(1L, "12345", "iphone_15", new BigDecimal("1000"), 1);
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        // When
        orderService.placeOrder(orderRequest);

        // Then
        verify(orderRepository, times(1)).save(any(Order.class));
    }
}
