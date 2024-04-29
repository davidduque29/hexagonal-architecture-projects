package co.com.flypass.jpa.postgresql.adapters.transfer;

import co.com.flypass.exception.BussinessException;
import co.com.flypass.jpa.postgresql.adapters.product.constants.ProductConstants;
import co.com.flypass.jpa.postgresql.adapters.product.entity.ProductEntity;
import co.com.flypass.jpa.postgresql.adapters.product.repository.ProductCrudRepository;
import co.com.flypass.jpa.postgresql.adapters.transaction.constants.TransactionConstants;
import co.com.flypass.jpa.postgresql.adapters.transaction.entity.TransactionEntity;
import co.com.flypass.jpa.postgresql.adapters.transaction.repository.TransactionCrudRepository;
import co.com.flypass.jpa.postgresql.adapters.transfer.constants.TransferConstants;
import co.com.flypass.jpa.postgresql.adapters.transfer.entity.TransferEntity;
import co.com.flypass.jpa.postgresql.adapters.transfer.mapper.TransferConverter;
import co.com.flypass.jpa.postgresql.adapters.transfer.repository.TransferCrudRepository;
import co.com.flypass.jpa.postgresql.adapters.transfer.repository.TransferRepository;
import co.com.flypass.jpa.postgresql.adapters.transfer.utils.TransferUtils;
import co.com.flypass.models.Transfer;
import co.com.flypass.ports.outbound.TransferGatewayPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class TransferAdapter implements TransferGatewayPort {
    private final TransferRepository transferRepository;
    private final TransferCrudRepository transferCrudRepository;
    private final TransactionCrudRepository transactionCrudRepository;
    private final ProductCrudRepository productCrudRepository;

    @Override
    public Transfer createTransfer(Transfer transferModel) {
        var transferEntity = convertToTransferEntity(transferModel);
        // Obtener las cuentas de envío y destino
        var sendingAccount = getProductEntityById(transferModel.getIdCuentaEnvio(), "Cuenta de envío");
        var destinationAccount = getProductEntityById(transferModel.getIdCuentaDestino(), "Cuenta de destino");
        double transferAmount = transferModel.getMonto();

        // Actualizar saldos de las cuentas
        sendingAccount = updateAccountBalance(sendingAccount, transferAmount, true);
        destinationAccount = updateAccountBalance(destinationAccount, transferAmount, false);

        // Crear y guardar la transacción asociada con la transferencia
        var transaction = createAndSaveTransaction(transferModel, sendingAccount);
        // Actualizar la entidad de transferencia con el número de referencia y el estado
        updateTransferEntity(transferEntity, sendingAccount);
        // Guardar la entidad de transferencia y convertirla a un objeto Transfer para retornarla
        TransferEntity savedTransfer = saveTransfer(transferEntity);
        return TransferConverter.convertEntityToTransfer(savedTransfer);
    }

    // Método para actualizar el saldo de una cuenta y guardarla en la base de datos
    private ProductEntity updateAccountBalance(ProductEntity account, double amount, boolean accountBalanceOperation) {
        account.setFechaModificacion(new Date());
        double updatedBalance;
        if (accountBalanceOperation) {
            updatedBalance = account.getSaldo() - amount;  // Restar el monto del saldo
        } else {
            updatedBalance = account.getSaldo() + amount;  // Sumar el monto al saldo
        }
        account.setSaldo(updatedBalance);
        return productCrudRepository.save(account);
    }

    // Método para crear una transacción y guardarla en la base de datos
    private TransactionEntity createAndSaveTransaction(Transfer transferModel, ProductEntity sendingAccount) {
        TransactionEntity transaction = createTransaction(transferModel, sendingAccount);
        return transactionCrudRepository.save(transaction);
    }

    // Método para crear una transacción asociada con la transferencia
    private TransactionEntity createTransaction(Transfer transferModel, ProductEntity sendingAccount) {
        return transactionCrudRepository.save(TransactionEntity.builder()
                .tipo(TransactionEntity.TipoTransaccion.valueOf(TransactionConstants.TRANSACTION_TRANSFER))
                .monto(transferModel.getMonto())
                .fechaTransaccion(new Date())
                .cuenta(sendingAccount)
                .build());
    }

    // Método para actualizar la entidad de transferencia con el número de referencia y el estado
    private void updateTransferEntity(TransferEntity transferEntity, ProductEntity sendingAccount) {
        transferEntity.setNumeroReferencia(generateUniqueReferenceNumber(sendingAccount.getTipoCuenta().toString()));
        transferEntity.setEstado(TransferEntity.EstadoTransferencia.COMPLETADA);
    }

    private ProductEntity getProductEntityById(Long productId, String errorMessagePrefix) {
        return productCrudRepository.findProductById(productId)
                .orElseThrow(() -> new BussinessException(
                        TransactionConstants.ERR_NOT_FOUND_STATUS,
                        errorMessagePrefix + " no encontrada",
                        TransactionConstants.ERR_NOT_FOUND_CODE));
    }

    private TransferEntity convertToTransferEntity(Transfer transfer) {
        transfer.setEstado(TransferConstants.TRANSFER_PENDING);
        return TransferConverter.convertTransferToEntity(transfer);
    }

    private TransferEntity saveTransfer(TransferEntity transferEntity) {
        transferEntity.setFechaTransferencia(new Date());
        return transferCrudRepository.save(transferEntity);
    }

    private String generateUniqueReferenceNumber(String tipoCuenta) {
        boolean isCuentaCorriente = tipoCuenta.equals(ProductConstants.SAVINGS_ACCOUNT_TYPE);
        String referenceNumber;
        do {
            referenceNumber = TransferUtils.generateReferenceNumber(isCuentaCorriente);
            // Verificar si el número de referencia ya existe en la base de datos
        } while (transferRepository.existsByNumeroReferencia(referenceNumber));
        return referenceNumber;
    }

    @Override
    public Transfer findTransferById(Long id) {
        TransferEntity transferEntity = transferCrudRepository.findTransferById(id)
                .orElseThrow(() -> new BussinessException(
                        TransactionConstants.ERR_NOT_FOUND_STATUS,
                        "Transferencia no encontrada con ID: " + id,
                        TransactionConstants.ERR_NOT_FOUND_CODE));
        return TransferConverter.convertEntityToTransfer(transferEntity);
    }
}
