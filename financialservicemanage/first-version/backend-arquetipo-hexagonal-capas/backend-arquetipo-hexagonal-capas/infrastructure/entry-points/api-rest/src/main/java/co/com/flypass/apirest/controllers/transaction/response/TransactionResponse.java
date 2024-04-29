package co.com.flypass.apirest.controllers.transaction.response;

 import co.com.flypass.apirest.controllers.controllermessage.StateMessage;
 import lombok.Data;

@Data
public class TransactionResponse<T> {
    private T transaction;
    private StateMessage stateMessage;
}
