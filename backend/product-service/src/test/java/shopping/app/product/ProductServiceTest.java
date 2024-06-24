package shopping.app.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("createProduct should save and return product")
    void createProductHappyPath() {
        ProductRequest productRequest = new ProductRequest("id", "name", "description", "skuCode", new BigDecimal("1000"));
        Product product = new Product("id", "name", "description", "skuCode", new BigDecimal("1000"));
        Product savedProduct = new Product("id", "name", "description", "skuCode", new BigDecimal("1000"));
        when(productRepository.save(any())).thenReturn(savedProduct);

        ProductResponse result = productService.createProduct(productRequest);

        verify(productRepository, times(1)).save(any());
        assertEquals(product.getId(), result.id());
        assertEquals(product.getName(), result.name());
        assertEquals(product.getDescription(), result.description());
        assertEquals(product.getSkuCode(), result.skuCode());
        assertEquals(product.getPrice(), result.price());
    }

    @Test
    @DisplayName("getAllProducts should return all products")
    void getAllProductsHappyPath() {
        Product product = new Product("id", "name", "description", "skuCode", new BigDecimal("1000"));
        when(productRepository.findAll()).thenReturn(Collections.singletonList(product));

        List<ProductResponse> result = productService.getAllProducts();

        verify(productRepository, times(1)).findAll();
        assertEquals(1, result.size());
        assertEquals(product.getId(), result.get(0).id());
        assertEquals(product.getName(), result.get(0).name());
        assertEquals(product.getDescription(), result.get(0).description());
        assertEquals(product.getSkuCode(), result.get(0).skuCode());
        assertEquals(product.getPrice(), result.get(0).price());
    }

    @Test
    @DisplayName("getAllProducts should return empty list when no products")
    void getAllProductsNoProducts() {
        when(productRepository.findAll()).thenReturn(Collections.emptyList());

        List<ProductResponse> result = productService.getAllProducts();

        verify(productRepository, times(1)).findAll();
        assertEquals(0, result.size());
    }
}