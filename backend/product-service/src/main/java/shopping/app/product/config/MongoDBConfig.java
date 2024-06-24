package shopping.app.product.config;

import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoDBConfig {

    @Value("${MONGO_DB_USERNAME}")
    private String username;

    @Value("${MONGO_DB_PASSWORD}")
    private String password;

    @Value("${MONGO_HOST}")
    private String host;

    @Value("${MONGO_PORT}")
    private int port;

    @Value("${MONGO_DB}")
    private String database;

    @Bean
    public MongoTemplate mongoTemplate() {
        String connection = String.format("mongodb://%s:%s@%s:%d/%s?authSource=admin", username, password, host, port, database);
        SimpleMongoClientDatabaseFactory factory = new SimpleMongoClientDatabaseFactory(MongoClients.create(connection), database);
        return new MongoTemplate(factory);
    }
}
