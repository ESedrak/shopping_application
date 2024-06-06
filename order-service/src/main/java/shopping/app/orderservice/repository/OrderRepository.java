package shopping.app.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shopping.app.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long > {
}
