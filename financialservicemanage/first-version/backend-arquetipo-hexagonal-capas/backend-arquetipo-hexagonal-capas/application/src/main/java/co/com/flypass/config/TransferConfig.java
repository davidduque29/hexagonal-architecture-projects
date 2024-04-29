package co.com.flypass.config;


import co.com.flypass.ports.outbound.TransferGatewayPort;
import co.com.flypass.usecase.UseCaseTransfer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class TransferConfig {
    @Primary
    @Bean
    public UseCaseTransfer transferUseCaseConfiguration(TransferGatewayPort transferGatewayPort){
        return new UseCaseTransfer(transferGatewayPort);
    }
}
