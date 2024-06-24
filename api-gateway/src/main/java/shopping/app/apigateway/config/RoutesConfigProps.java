package shopping.app.apigateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "services.url")
public class RoutesConfigProps {

    private String product;
    private String order;
    private String inventory;
    private String frontend;
}
