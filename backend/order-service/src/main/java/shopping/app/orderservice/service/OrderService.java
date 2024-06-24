package shopping.app.orderservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import shopping.app.orderservice.client.InventoryClient;
import shopping.app.orderservice.dto.OrderRequest;
import shopping.app.orderservice.model.Order;
import shopping.app.orderservice.repository.OrderRepository;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public void placeOrder(OrderRequest orderRequest){
        var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

        if (isProductInStock) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setSkuCode(orderRequest.skuCode());
            order.setQuantity(orderRequest.quantity());

            orderRepository.save(order);
            log.info("Order Placed");
        } else {
            throw new RuntimeException("Product with skuCode +" + orderRequest.skuCode() + " is not in stock");
        }

    }
}
