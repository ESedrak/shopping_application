package shopping.app.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import shopping.app.product.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
