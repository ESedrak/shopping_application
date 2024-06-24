package shopping.app.orderservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "services.inventory")
public class RestClientConfigProps {
    private String url;
}
