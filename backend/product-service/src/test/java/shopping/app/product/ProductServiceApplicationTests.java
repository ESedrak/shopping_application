package shopping.app.product;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		mongoDBContainer.start();
	}

	@Test
	void createProduct_givenValid_success() {
		String requestBody = """
				{
					"name": "name",
				    "description": "description",
				    "price": 1000
				}
				""";

		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/product")
				.then()
				.statusCode(201)
				.body("id", Matchers.notNullValue())
				.body("name", Matchers.equalTo("name"))
				.body("description", Matchers.equalTo("description"))
				.body("price", Matchers.equalTo(1000));
	}

	@Test
	void createProduct_givenBadRequest_throws() {
		String badRequestBody = """
				{
					"name": "badRequest",
				    "description": 1,
				    "price": "should be an integer"
				}
				""";

		RestAssured.given()
				.contentType("application/json")
				.body(badRequestBody)
				.when()
				.post("/api/product")
				.then()
				.statusCode(400);
	}

	@Test
	void getProduct_givenValid_success() {
		RestAssured.given()
				.contentType("application/json")
				.when()
				.get("/api/product")
				.then()
				.statusCode(200);
	}

}
