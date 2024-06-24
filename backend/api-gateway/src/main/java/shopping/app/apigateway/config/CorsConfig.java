package shopping.app.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

// Frontend CORS error if this config not added
// Access to XMLHttpRequest Has Been Blocked by CORS Policy
@Configuration
public class CorsConfig {

    private final RoutesConfigProps routesConfigProps;

    public CorsConfig(RoutesConfigProps routesConfigProps) {
        this.routesConfigProps = routesConfigProps;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin(routesConfigProps.getFrontend());
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}