package co.com.flypass.apirest.controllers.product.helper;


import co.com.flypass.apirest.controllers.controllermessage.StateMessage;
import co.com.flypass.apirest.controllers.product.request.ProductRequest;
import co.com.flypass.apirest.controllers.product.response.ProductResponse;
import co.com.flypass.exception.BussinessException;
import co.com.flypass.models.Product;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ProductCreationHelper {

    public Product convertRequestToProduct(ProductRequest request) {
        // validar si el requeste es nulo - pendiente
        var product = Product.builder()
                .tipoCuenta(request.getTipoCuenta())
                .numeroCuenta(request.getNumeroCuenta())
                .estado(request.getEstado())
                .saldo(request.getSaldo())
                .exentaGMF(request.isExentaGMF())
                .fechaCreacion(request.getFechaCreacion())
                .fechaModificacion(request.getFechaModificacion())
                .clienteId(request.getClienteId())
                .build();
        product.validProduct();
        return product;
    }

    public StateMessage getStateMessages(BussinessException exc) {
        return StateMessage.builder()
                .status(exc.getStatus())
                .detail(exc.getDetail())
                .statusCode(Integer.parseInt(exc.getCode()))
                .build();
    }

    //crear el productResponse de exito que va a devolver en el try
    public ProductResponse createResponse(Product product) {
        var productResponse = new ProductResponse<>();
        var stateMessage = StateMessage.builder()
                .statusCode(200)
                .status("OK")
                .title("Success")
                .detail("Operation completed successfully")
                .build();
        productResponse.setProduct(product);
        productResponse.setStateMessage(stateMessage);
        return productResponse;
    }

    public ProductResponse createResponse(Optional<Product> product) {
        var productResponse = new ProductResponse<>();
        var stateMessage = StateMessage.builder()
                .statusCode(200)
                .status("OK")
                .title("Success")
                .detail("Operation completed successfully")
                .build();
        productResponse.setProduct(product);
        productResponse.setStateMessage(stateMessage);
        return productResponse;
    }

    public ProductResponse createBadResponse() {
        var productResponse = new ProductResponse<>();
        var stateMessage = StateMessage.builder()
                .statusCode(404)
                .status("NOT_FOUND")
                .title("Resource Not Found")
                .detail("The requested resource was not found on the server")
                .build();
        productResponse.setProduct("{}");
        productResponse.setStateMessage(stateMessage);
        return productResponse;
    }
}
