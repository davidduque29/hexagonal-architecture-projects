package co.com.flypass.apirest.controllers.customer.response;

import co.com.flypass.apirest.controllers.controllermessage.StateMessage;
import lombok.Data;

@Data
public class CustomerResponse<T> {
    private T client;
    private StateMessage stateMessage;
}
