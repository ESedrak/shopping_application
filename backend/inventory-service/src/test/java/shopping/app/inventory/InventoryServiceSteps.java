package shopping.app.inventory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import shopping.app.inventory.repository.InventoryRepository;
import shopping.app.inventory.service.InventoryService;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class InventoryServiceSteps {

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private InventoryService inventoryService;

    private boolean result;

    @Given("a product with skuCode {string} and quantity {int} in the inventory")
    public void aProductInInventory(String skuCode, int quantity) {
        MockitoAnnotations.openMocks(this);
        when(inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(eq(skuCode), anyInt()))
                .thenAnswer(invocation -> (Integer) invocation.getArgument(1) <= quantity);
    }

    @When("a stock check is requested for skuCode {string} and quantity {int}")
    public void aStockCheckIsRequested(String skuCode, int quantity) {
        result = inventoryService.isInStock(skuCode, quantity);
    }

    @Then("the service should return true")
    public void theServiceShouldReturnTrue() {
        Assertions.assertTrue(result);
    }

    @Then("the service should return false")
    public void theServiceShouldReturnFalse() {
        Assertions.assertFalse(result);
    }
}