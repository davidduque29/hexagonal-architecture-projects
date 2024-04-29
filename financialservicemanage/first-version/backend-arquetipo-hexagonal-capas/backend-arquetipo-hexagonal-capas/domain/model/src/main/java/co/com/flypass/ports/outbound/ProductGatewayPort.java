package co.com.flypass.ports.outbound;

import co.com.flypass.models.Product;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductGatewayPort {
    Product createProduct(Product product);
    Optional<Product> updateProductId(Long productId, Product product);
    List<Product> findAllProducts();
    Optional<Product> findProductById(Long id);
    void deleteProduct(Long id);

    void updateAccountBalanceById(Long productId,Double monto);
    Product findCountNumberByCountNumber(String numerocuenta);
    int countProductsByClient(Long clienteId);
    int countExistingClientById(Long clienteId);
}
