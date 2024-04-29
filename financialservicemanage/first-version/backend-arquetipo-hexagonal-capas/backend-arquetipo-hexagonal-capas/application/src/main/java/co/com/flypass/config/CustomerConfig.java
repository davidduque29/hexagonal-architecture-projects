package co.com.flypass.config;

import co.com.flypass.ports.outbound.CustomerGatewayPort;
import co.com.flypass.ports.outbound.ProductGatewayPort;
import co.com.flypass.usecase.UseCaseCustomer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class CustomerConfig {
    @Primary
    @Bean
    public UseCaseCustomer customerUseCaseConfiguration(CustomerGatewayPort customerGatewayPort
            , ProductGatewayPort productGatewayPort){
        return new UseCaseCustomer(customerGatewayPort, productGatewayPort);
    }

}
