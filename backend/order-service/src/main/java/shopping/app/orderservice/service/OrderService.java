package shopping.app.orderservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import shopping.app.orderservice.client.InventoryClient;
import shopping.app.orderservice.dto.OrderRequest;
import shopping.app.orderservice.event.OrderPlacedEvent;
import shopping.app.orderservice.model.Order;
import shopping.app.orderservice.repository.OrderRepository;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public void placeOrder(OrderRequest orderRequest){
        validateProductStock(orderRequest);
        Order order = createAndSaveOrder(orderRequest);
        sendOrderPlacedEvent(order, orderRequest.userDetails().email());
    }

    private void validateProductStock(OrderRequest orderRequest) {
        var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
        if (!isProductInStock) {
            throw new RuntimeException("Product with skuCode +" + orderRequest.skuCode() + " is not in stock");
        }
    }

    private Order createAndSaveOrder(OrderRequest orderRequest) {
        var order = mapToOrder(orderRequest);
        return orderRepository.save(order);
    }

    private void sendOrderPlacedEvent(Order order, String email) {
        OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent(order.getOrderNumber(), email);
        log.info("Start - Sending OrderPlacedEvent {} event to kafka topic", orderPlacedEvent);
        kafkaTemplate.send("order-placed", orderPlacedEvent);
        log.info("End - Sending OrderPlacedEvent {} event to kafka topic", orderPlacedEvent);
    }

    private static Order mapToOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setPrice(orderRequest.price());
        order.setQuantity(orderRequest.quantity());
        order.setSkuCode(orderRequest.skuCode());
        return order;
    }
}
