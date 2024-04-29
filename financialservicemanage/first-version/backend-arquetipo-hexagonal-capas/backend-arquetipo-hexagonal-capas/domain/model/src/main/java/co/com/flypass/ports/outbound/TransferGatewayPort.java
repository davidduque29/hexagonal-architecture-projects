package co.com.flypass.ports.outbound;


import co.com.flypass.models.Transfer;

public interface TransferGatewayPort {
    Transfer createTransfer(Transfer transferModel);
    Transfer findTransferById(Long id);

}
