package co.com.flypass.usecase;

import co.com.flypass.models.Client;
import co.com.flypass.ports.inbound.ClientUseCasePort;
import co.com.flypass.ports.outbound.ClientRepositoryPort;


public class ClientUseCase implements ClientUseCasePort {

    private final ClientRepositoryPort clientRepositoryPort;


    public ClientUseCase(ClientRepositoryPort clientRepositoryPort) {
        this.clientRepositoryPort = clientRepositoryPort;
    }
    //todo: Agregar logica de negocio necesaria para el buen funcionamiento.

    public Client save(Client client) {
        return clientRepositoryPort.save(client);
    }

    public Client update(Client client) {
        return clientRepositoryPort.update(client);
    }

    public void deleteClientById(Long clientId){
        clientRepositoryPort.delete(clientId);

    }
}
