package shopping.app.orderservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import shopping.app.orderservice.dto.OrderRequest;
import shopping.app.orderservice.service.OrderService;

@Controller
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest order) {
        orderService.placeOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body("Order placed successfully");
    }
}
