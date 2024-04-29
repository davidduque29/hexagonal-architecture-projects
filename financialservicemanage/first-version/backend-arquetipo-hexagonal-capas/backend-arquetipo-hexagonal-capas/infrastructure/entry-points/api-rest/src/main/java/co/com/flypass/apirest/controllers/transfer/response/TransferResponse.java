package co.com.flypass.apirest.controllers.transfer.response;

import co.com.flypass.apirest.controllers.controllermessage.StateMessage;
import lombok.Data;

@Data
public class TransferResponse<T> {
    private T transfer;
    private StateMessage stateMessage;
}
