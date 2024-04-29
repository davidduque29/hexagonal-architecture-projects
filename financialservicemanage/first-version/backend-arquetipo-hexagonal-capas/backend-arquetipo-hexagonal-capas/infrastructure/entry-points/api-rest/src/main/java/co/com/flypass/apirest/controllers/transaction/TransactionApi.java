package co.com.flypass.apirest.controllers.transaction;


 import co.com.flypass.apirest.controllers.transaction.helper.TransactionCreationHelper;
 import co.com.flypass.apirest.controllers.transaction.request.TransactionRequest;
 import co.com.flypass.apirest.controllers.transaction.response.TransactionResponse;
 import co.com.flypass.exception.BussinessException;
 import co.com.flypass.models.Transaction;
 import co.com.flypass.ports.inbound.TransactionService;
 import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionApi {
    private Transaction transaction;
    private TransactionResponse<Transaction> transactionResponse;
    private final TransactionService transactionService;
    private final TransactionCreationHelper transactionCreationHelper;

    @PostMapping("/createtransaction")
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody TransactionRequest transactionRequest) {
        transactionResponse = new TransactionResponse<>();
        try {
            transaction = transactionService.createTransaction(transactionCreationHelper.convertRequestToTransaction(transactionRequest));
            transactionResponse = transactionCreationHelper.createResponse(transaction);
            return new ResponseEntity(transactionResponse, HttpStatus.OK);
        } catch (BussinessException exc) {
            transactionResponse = new TransactionResponse<>();
            transactionResponse.setStateMessage(transactionCreationHelper.getStateMessages(exc));
            return new ResponseEntity<>(transactionResponse, HttpStatus.BAD_REQUEST);
        }
    }

}
