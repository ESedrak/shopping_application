package shopping.app.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shopping.app.inventoryservice.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    boolean existsBySkuCodeAndQuantityIsGreaterThanEqual(String skuCode, Integer quantity);
}
