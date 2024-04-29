package co.com.flypass.usecase;


import co.com.flypass.models.Product;
import co.com.flypass.ports.outbound.ProductGatewayPort;
import co.com.flypass.ports.inbound.ProductService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UseCaseProduct implements ProductService {
    private final ProductGatewayPort productGatewayPort;

    @Override
    public Product createProduct(Product product) {
        return productGatewayPort.createProduct(product);
    }

    @Override
    public Optional<Product> updateProductId(Long productId, Product product) {
        return productGatewayPort.updateProductId(productId,product);
    }

    @Override
    public List<Product> findAllProducts() {
        return productGatewayPort.findAllProducts();
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return productGatewayPort.findProductById(id);
    }

    @Override
    public void deleteProduct(Long id) {
        productGatewayPort.deleteProduct(id);
    }
}
