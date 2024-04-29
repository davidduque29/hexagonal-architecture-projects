package co.com.flypass.apirest.controllers.transfer.helper;


 import co.com.flypass.apirest.controllers.controllermessage.StateMessage;
 import co.com.flypass.apirest.controllers.transfer.request.TransferRequest;
 import co.com.flypass.apirest.controllers.transfer.response.TransferResponse;
 import co.com.flypass.exception.BussinessException;
 import co.com.flypass.models.Transfer;
 import org.springframework.stereotype.Service;


@Service
public class TransferCreationHelper {
    public Transfer convertRequestToTransfer(TransferRequest request) {
        // validar si el requeste es nulo
        var transfer = Transfer.builder()
                .idCuentaEnvio(request.getIdCuentaEnvio())
                .idCuentaDestino(request.getIdCuentaRecepcion())
                .monto(request.getMonto())
                .descripcion(request.getDescripcion())
                .build();
        transfer.validTransfer();
        return transfer;
    }
    public StateMessage getStateMessages(BussinessException exc) {
        return StateMessage.builder()
                .status(exc.getStatus())
                .detail(exc.getDetail())
                .statusCode(Integer.parseInt(exc.getCode()))
                .build();
    }
    public TransferResponse<Transfer> createResponse(Transfer transfer) {
        TransferResponse<Transfer> transferResponse = new TransferResponse<>();
        StateMessage stateMessage = StateMessage.builder()
                .statusCode(200)
                .status("OK")
                .title("Success")
                .detail("Transfer Operation completed successfully")
                .build();
        transferResponse.setTransfer(transfer);
        transferResponse.setStateMessage(stateMessage);
        return transferResponse;
    }
}
