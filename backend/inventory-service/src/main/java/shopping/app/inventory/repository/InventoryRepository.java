package shopping.app.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shopping.app.inventory.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    boolean existsBySkuCodeAndQuantityIsGreaterThanEqual(String skuCode, Integer quantity);
}
