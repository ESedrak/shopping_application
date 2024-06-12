package shopping.app.product.configuration;

import com.mongodb.client.MongoClients;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
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
    private String port;

    @Value("${MONGO_DB}")
    private String database;

    @Bean
    public MongoTemplate mongoTemplate() {
        String connectionString = "mongodb://" + username + ":" + password + "@" + host + ":" + port + "/" + database + "?authSource=admin";
        SimpleMongoClientDatabaseFactory factory = new SimpleMongoClientDatabaseFactory(MongoClients.create(connectionString), database);
        return new MongoTemplate(factory);
    }
}
