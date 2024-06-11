package shopping.app.orderservice;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.testcontainers.containers.MySQLContainer;
import shopping.app.orderservice.stubs.InventoryClientStub;

import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
public class OrderServiceApplicationTest {
    @ServiceConnection
    static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.3.0");

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    static {
        mySQLContainer.start();
    }

    @Test
    void createOrder_givenValid_success() {
        String requestBody = """
                {
                	"skuCode": "iphone_15",
                    "quantity": 1,
                    "price": 1000
                }""";

        InventoryClientStub.stubInventoryCall("iphone_15", 1);

        RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/order")
                .then()
                .statusCode(201)
                .body(equalTo("Order placed successfully"));
    }

    @Test
    void createOrder_givenNotInStock_throws() {
        String requestBody = """
                {
                	"skuCode": "iphone_15",
                    "quantity": 100001,
                    "price": 1000
                }""";

        InventoryClientStub.stubInventoryErrorCall("iphone_15", 100001);

        RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/order")
                .then()
                .statusCode(500);
    }
}
