package co.com.flypass.usecase;

 import co.com.flypass.models.Transaction;
 import co.com.flypass.ports.outbound.TransactionGatewayPort;
 import co.com.flypass.ports.inbound.TransactionService;
 import lombok.RequiredArgsConstructor;

 import java.util.List;
 import java.util.Optional;


@RequiredArgsConstructor
public class UseCaseTransaction implements TransactionService {
    private final TransactionGatewayPort transactionGatewayPort;

    @Override
    public Transaction createTransaction(Transaction transactionEntity) {
        return transactionGatewayPort.createTransaction(transactionEntity);
    }

    @Override
    public Optional<Transaction> updateTransactionId(Long transactionId, Transaction transactionInput) {
        return transactionGatewayPort.updateTransactionId(transactionId, transactionInput);
    }

    @Override
    public List<Transaction> findAllTransactions() {
        return transactionGatewayPort.findAllTransactions();
    }

    @Override
    public Optional<Transaction> findTransactionById(Long id) {
        return transactionGatewayPort.findTransactionById(id);
    }

    @Override
    public void deleteTransaction(Long id) {
        transactionGatewayPort.deleteTransaction(id);
    }
}
