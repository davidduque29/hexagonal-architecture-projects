package co.com.flypass.apirest.controllers.transfer;



import co.com.flypass.apirest.controllers.transfer.helper.TransferCreationHelper;
import co.com.flypass.apirest.controllers.transfer.request.TransferRequest;
import co.com.flypass.apirest.controllers.transfer.response.TransferResponse;
import co.com.flypass.exception.BussinessException;
import co.com.flypass.models.Transfer;
import co.com.flypass.ports.inbound.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class TranferApi {
    private Transfer transfer;
    private TransferResponse<Transfer> transferResponse;
    private final TransferService transferService;
    private final TransferCreationHelper transferCreationHelper;

    @PostMapping("/createtransfer")
    public ResponseEntity<TransferResponse> createTransfer(@RequestBody TransferRequest transferRequest) {
        transferResponse = new TransferResponse<>();
        try {
            transfer = transferService.createTransfer(transferCreationHelper.convertRequestToTransfer(transferRequest));
            transferResponse = transferCreationHelper.createResponse(transfer);
            return new ResponseEntity(transferResponse, HttpStatus.OK);
        } catch (BussinessException exc) {
            transferResponse = new TransferResponse<>();
            transferResponse.setStateMessage(transferCreationHelper.getStateMessages(exc));
            return new ResponseEntity<>(transferResponse, HttpStatus.BAD_REQUEST);
        }
    }

}
