package co.com.flypass.config;


import co.com.flypass.ports.outbound.ProductGatewayPort;
import co.com.flypass.usecase.UseCaseProduct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ProductConfig {
    @Primary
    @Bean
    public UseCaseProduct productUseCaseConfiguration(ProductGatewayPort productGatewayPort){
        return new UseCaseProduct(productGatewayPort);
    }
}
