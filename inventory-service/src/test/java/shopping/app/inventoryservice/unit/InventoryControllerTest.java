package shopping.app.inventoryservice.unit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import shopping.app.inventoryservice.service.InventoryService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest(properties = {
        "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"
})
@AutoConfigureMockMvc
class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryService inventoryService;

    @Test
    void testIsInStock() throws Exception {
        // Mock the service layer
        when(inventoryService.isInStock("iphone_15", 1)).thenReturn(true);

        // Send GET request with query parameters
        mockMvc.perform(MockMvcRequestBuilders.get("/api/inventory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .queryParam("skuCode", "iphone_15")
                        .queryParam("quantity", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}
