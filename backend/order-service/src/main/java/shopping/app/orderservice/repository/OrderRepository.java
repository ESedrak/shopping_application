package shopping.app.orderservice.repository;

import io.micrometer.observation.annotation.Observed;
import org.springframework.data.jpa.repository.JpaRepository;
import shopping.app.orderservice.model.Order;

@Observed
public interface OrderRepository extends JpaRepository<Order, Long > {
}
