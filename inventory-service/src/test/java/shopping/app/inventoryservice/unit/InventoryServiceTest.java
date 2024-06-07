package shopping.app.inventoryservice.unit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shopping.app.inventoryservice.repository.InventoryRepository;
import shopping.app.inventoryservice.service.InventoryService;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InventoryServiceTest {
    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private InventoryService inventoryService;

    @Test
    void testCheckInventoryInStock() {
        // Given
        String skuCode = "iphone_15";
        int quantity = 1;
        when(inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity)).thenReturn(true);

        // When
        boolean isInStock = inventoryService.isInStock(skuCode, quantity);

        // Then
        assertTrue(isInStock);
        verify(inventoryRepository, times(1)).existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
    }
}
