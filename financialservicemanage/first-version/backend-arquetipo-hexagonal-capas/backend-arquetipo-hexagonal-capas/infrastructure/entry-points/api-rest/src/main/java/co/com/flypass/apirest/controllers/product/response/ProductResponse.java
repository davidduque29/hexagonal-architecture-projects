package co.com.flypass.apirest.controllers.product.response;

 import co.com.flypass.apirest.controllers.controllermessage.StateMessage;
 import lombok.Data;

@Data
public class ProductResponse<T> {
    private T product;
    private StateMessage stateMessage;
}
