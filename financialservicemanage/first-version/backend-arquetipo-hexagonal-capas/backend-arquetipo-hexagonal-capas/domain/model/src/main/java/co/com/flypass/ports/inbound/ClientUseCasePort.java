package co.com.flypass.ports.inbound;


import co.com.flypass.models.Client;

public interface ClientUseCasePort {

    Client save(Client client);

    Client update(Client client);

    void deleteClientById(Long clientId);

}
