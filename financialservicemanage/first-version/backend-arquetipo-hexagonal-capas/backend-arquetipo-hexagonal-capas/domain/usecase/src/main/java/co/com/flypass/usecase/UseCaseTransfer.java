package co.com.flypass.usecase;


import co.com.flypass.models.Transfer;
import co.com.flypass.ports.outbound.TransferGatewayPort;
import co.com.flypass.ports.inbound.TransferService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UseCaseTransfer implements TransferService {
    private final TransferGatewayPort transferGatewayPort;

    @Override
    public Transfer createTransfer(Transfer transferEntity) {
        return transferGatewayPort.createTransfer(transferEntity);
    }

    @Override
    public Transfer findTransferById(Long id) {
        return transferGatewayPort.findTransferById(id);
    }

}
