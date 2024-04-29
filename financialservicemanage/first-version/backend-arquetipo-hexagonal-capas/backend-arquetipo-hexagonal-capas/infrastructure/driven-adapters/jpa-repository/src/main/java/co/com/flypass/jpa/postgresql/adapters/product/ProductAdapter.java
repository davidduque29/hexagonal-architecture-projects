package co.com.flypass.jpa.postgresql.adapters.product;

 import co.com.flypass.exception.BussinessException;
 import co.com.flypass.jpa.postgresql.adapters.customer.entity.CustomerEntity;
 import co.com.flypass.jpa.postgresql.adapters.product.constants.ProductConstants;
 import co.com.flypass.jpa.postgresql.adapters.product.entity.ProductEntity;
 import co.com.flypass.jpa.postgresql.adapters.product.mapperproduct.ProductConverter;
 import co.com.flypass.jpa.postgresql.adapters.product.repository.ProductCrudRepository;
 import co.com.flypass.jpa.postgresql.adapters.product.repository.ProductQueryRepository;
 import co.com.flypass.jpa.postgresql.adapters.product.utils.ProductUtils;
 import co.com.flypass.models.Product;
 import co.com.flypass.ports.outbound.ProductGatewayPort;
 import org.springframework.stereotype.Service;

import java.util.Date;
 import java.util.List;
 import java.util.Optional;


@Service
public class ProductAdapter implements ProductGatewayPort {
    private final ProductCrudRepository productCrudRepository;
    private final ProductQueryRepository businessRules;
    private final ProductConverter productConverter;

    public ProductAdapter(ProductCrudRepository productCrudRepository, ProductQueryRepository businessRules
            , ProductConverter productConverter) {
        this.productCrudRepository = productCrudRepository;
        this.businessRules = businessRules;
        this.productConverter = productConverter;
    }

    @Override
    public Product createProduct(Product productModel) {
        validateCustomerExistence(productModel.getClienteId());
        validateBalance(productModel.getSaldo());

        ProductEntity product = convertModelToEntity(productModel);
        setAccountNumber(productModel, product);
        setDefaultValues(product);
        saveProduct(product);

        return convertToProduct(product);
    }

    private void validateCustomerExistence(Long clientId) {
        int numClientes = businessRules.countExistingClientById(clientId);
        if (numClientes == 0) {
            throw new BussinessException("ERROR_CUSTOMER_NOT_FOUND",
                    "El cliente especificado no existe en la base de datos.",
                    "404");
        }
    }

    private void validateBalance(double saldo) {
        if (saldo <= 0) {
            throw new BussinessException("ERROR_NEGATIVE_BALANCE",
                    "La cuenta de ahorros no puede tener un saldo menor a $0.",
                    "422");
        }
    }

    private ProductEntity convertModelToEntity(Product productModel) {

        return productConverter.convertProductToEntity(productModel);
    }

    private void setAccountNumber(Product productModel, ProductEntity productEntity) {
        String numeroCuenta;
        if (ProductConstants.SAVINGS_ACCOUNT_TYPE.equals(productModel.getTipoCuenta())) {
            numeroCuenta = ProductUtils.generateAccountNumber(false);
        } else {
            numeroCuenta = ProductUtils.generateAccountNumber(true);
        }
        productEntity.setNumeroCuenta(numeroCuenta);
    }

    private void setDefaultValues(ProductEntity productEntity) {
        productEntity.setEstado(ProductEntity.EstadoCuenta.ACTIVA);
        productEntity.setFechaCreacion(new Date());
        // Asignar cliente al producto
        CustomerEntity cliente = new CustomerEntity();
        cliente.setId(productEntity.getCliente().getId());
        productEntity.setCliente(cliente);
    }

    private void saveProduct(ProductEntity productEntity) {
        productCrudRepository.save(productEntity);
    }

    private Product convertToProduct(ProductEntity productEntity) {
        return productConverter.convertEntityToProduct(productEntity);
    }


    @Override
    public Optional<Product> updateProductId(Long productId, Product productModel) {


        return productCrudRepository.findById(productId).map(productEntity -> {
            // Verifica si el estado de la cuenta es "CANCELADA" y el saldo es igual a 0
            if (productModel.getSaldo() == 0) {
                // Actualiza los campos de la entidad con los valores proporcionados
                productEntity.setTipoCuenta(productConverter.convertElementToTipoCuenta(productModel.getTipoCuenta()));
                productEntity.setNumeroCuenta(productModel.getNumeroCuenta());
                productEntity.setEstado(productConverter.convertElementToAccountState(productModel.getEstado()));
                productEntity.setSaldo(productModel.getSaldo());
                productEntity.setExentaGMF(productModel.isExentaGMF());
                productEntity.setFechaCreacion(productModel.getFechaCreacion());
                productEntity.setFechaModificacion(productModel.getFechaModificacion());

                // Guarda la entidad actualizada en la base de datos
                return productConverter.convertEntityToProduct(productCrudRepository.save(productEntity));
            } else {
                // Si no se cumple la condición de cancelación, lanza una excepción BussinessException
                throw new BussinessException("Bad Request"
                        , "No se pudo cancelar la cuenta porque el saldo no es igual a $0"
                        , "400");
            }
        });
    }

    @Override
    public List<Product> findAllProducts() {
        return productConverter.convertToProductList(productCrudRepository.findAll());
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return productConverter.convertEntityToProduct(productCrudRepository.findById(id));
    }

    @Override
    public void deleteProduct(Long id) {
        productCrudRepository.delete(ProductEntity.builder()
                .id(id).build());
    }

    @Override
    public void updateAccountBalanceById(Long productId, Double monto) {
    }

    @Override
    public Product findCountNumberByCountNumber(String numerocuenta) {
        return productConverter.convertEntityToProduct(productCrudRepository
                .findCountNumberByCountNumber(numerocuenta));
    }

    @Override
    public int countProductsByClient(Long clienteId) {
        return productCrudRepository.countProductsByClient(clienteId);
    }

    @Override
    public int countExistingClientById(Long clienteId) {
        return  productCrudRepository.countExistingClientById(clienteId);
    }

}

