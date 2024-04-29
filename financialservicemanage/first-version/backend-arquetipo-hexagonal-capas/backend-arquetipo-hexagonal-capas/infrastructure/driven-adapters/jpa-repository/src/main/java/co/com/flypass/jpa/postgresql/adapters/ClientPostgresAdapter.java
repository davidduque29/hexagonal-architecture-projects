package co.com.flypass.jpa.postgresql.adapters;

import co.com.flypass.jpa.postgresql.mappers.IClientEntityMapper;
import co.com.flypass.jpa.postgresql.repositories.ClientRepository;
import co.com.flypass.models.Client;
import co.com.flypass.ports.outbound.ClientRepositoryPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class ClientPostgresAdapter implements ClientRepositoryPort {
    private final ClientRepository clientRepository;
    private final IClientEntityMapper clientEntityMapper;

    public ClientPostgresAdapter(ClientRepository clientRepository, IClientEntityMapper clientEntityMapper) {
        this.clientRepository = clientRepository;
        this.clientEntityMapper = clientEntityMapper;
    }


    @Override
    public Client save(Client client) {
        return clientEntityMapper.toClient(clientRepository.save(clientEntityMapper.toClientEntity(client)));
    }

    @Override
    public Client update(Client client) {
        return clientEntityMapper.toClient(clientRepository.save(clientEntityMapper.toClientEntity(client)));
    }


    @Override
    public void delete(Long clientId) {
        clientRepository.deleteById(clientId);
    }
}
