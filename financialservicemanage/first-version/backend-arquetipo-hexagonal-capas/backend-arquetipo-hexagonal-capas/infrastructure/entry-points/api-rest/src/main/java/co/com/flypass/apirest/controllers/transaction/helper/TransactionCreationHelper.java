package co.com.flypass.apirest.controllers.transaction.helper;


 import co.com.flypass.apirest.controllers.controllermessage.StateMessage;
 import co.com.flypass.apirest.controllers.transaction.request.TransactionRequest;
 import co.com.flypass.apirest.controllers.transaction.response.TransactionResponse;
 import co.com.flypass.exception.BussinessException;
 import co.com.flypass.models.Transaction;
 import org.springframework.stereotype.Service;


@Service
public class TransactionCreationHelper {
    private Transaction transaction;
    private StateMessage stateMessage;
    private TransactionResponse transactionResponse;

    public Transaction convertRequestToTransaction(TransactionRequest request) {
        // validar si el requeste es nulo - pendiente
        transaction = Transaction.builder()
                .tipo(request.getTipo())
                .monto(request.getMonto())
                .fechaTransaccion(request.getFechaTransaccion())
                .cuentaId(request.getCuentaId())
                .build();
        transaction.validTransaction();
        return transaction;
    }

    public StateMessage getStateMessages(BussinessException exc) {
        return StateMessage.builder()
                .status(exc.getStatus())
                .detail(exc.getDetail())
                .statusCode(Integer.parseInt(exc.getCode()))
                .build();
    }

    //crear el productResponse de exito que va a devolver en el try
    public TransactionResponse createResponse(Transaction transaction ) {
        transactionResponse = new TransactionResponse<>();
        stateMessage = StateMessage.builder()
                .statusCode(200)
                .status("OK")
                .title("Success")
                .detail("Operation completed successfully")
                .build();
        transactionResponse.setTransaction(transaction);
        transactionResponse.setStateMessage(stateMessage);
        return transactionResponse;
    }
}
