package co.com.flypass.ports.outbound;

import co.com.flypass.models.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionGatewayPort {
    Transaction createTransaction(Transaction transactionModel);
    Optional<Transaction> updateTransactionId(Long transactionId, Transaction transactionInput);
    List<Transaction> findAllTransactions();
    Optional<Transaction> findTransactionById(Long id);
    void deleteTransaction(Long id);
}
