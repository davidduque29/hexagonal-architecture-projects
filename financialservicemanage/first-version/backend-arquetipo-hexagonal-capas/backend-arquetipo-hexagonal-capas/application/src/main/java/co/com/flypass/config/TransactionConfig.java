package co.com.flypass.config;

 import co.com.flypass.ports.outbound.TransactionGatewayPort;
 import co.com.flypass.usecase.UseCaseTransaction;
 import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class TransactionConfig {
    @Primary
    @Bean
    public UseCaseTransaction transactionUseCaseConfiguration(TransactionGatewayPort transactionGatewayPort){
        return new UseCaseTransaction(transactionGatewayPort);
    }
}
