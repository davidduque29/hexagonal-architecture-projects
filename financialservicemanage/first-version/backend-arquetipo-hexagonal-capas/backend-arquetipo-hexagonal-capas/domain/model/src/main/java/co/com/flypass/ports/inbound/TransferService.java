package co.com.flypass.ports.inbound;


import co.com.flypass.models.Transfer;

public interface TransferService {
    Transfer createTransfer(Transfer transferEntity);
    Transfer findTransferById(Long id);

}
