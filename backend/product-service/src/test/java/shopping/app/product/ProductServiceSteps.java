package shopping.app.product;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import shopping.app.product.dto.ProductRequest;
import shopping.app.product.dto.ProductResponse;
import shopping.app.product.model.Product;
import shopping.app.product.repository.ProductRepository;
import shopping.app.product.service.ProductService;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ProductServiceSteps {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private ProductResponse result;

    @Given("a product request with name {string}, description {string}, skuCode {string}, and price {string}")
    public void aProductRequest(String name, String description, String skuCode, String price) {
        MockitoAnnotations.openMocks(this);
        ProductRequest productRequest = new ProductRequest(name, description, skuCode, new BigDecimal(price));
        Product product = new Product("id", name, description, skuCode, new BigDecimal(price));
        when(productRepository.save(any())).thenReturn(product);
        result = productService.createProduct(productRequest);
    }

    @When("the product is created")
    public void theProductIsCreated() {
        // The product is already created in the Given step
    }

    @Then("the product should be saved and returned with an id")
    public void theProductShouldBeSavedAndReturnedWithAnId() {
        Assertions.assertNotNull(result.id());
    }

    @Given("there are products in the repository")
    public void thereAreProductsInTheRepository() {
        MockitoAnnotations.openMocks(this);
        Product product = new Product("id", "name", "description", "skuCode", new BigDecimal("1000"));
        when(productRepository.findAll()).thenReturn(Collections.singletonList(product));
    }

    @When("all products are requested")
    public void allProductsAreRequested() {
        List<ProductResponse> products = productService.getAllProducts();
        result = products.isEmpty() ? null : products.get(0);
    }

    @Then("all products should be returned")
    public void allProductsShouldBeReturned() {
        Assertions.assertNotNull(result);
    }

    @Given("there are no products in the repository")
    public void thereAreNoProductsInTheRepository() {
        MockitoAnnotations.openMocks(this);
        when(productRepository.findAll()).thenReturn(Collections.emptyList());
    }

    @Then("an empty list should be returned")
    public void anEmptyListShouldBeReturned() {
        Assertions.assertTrue(productService.getAllProducts().isEmpty());
    }
}