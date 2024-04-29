package co.com.flypass.apirest.handlers.implement;

import co.com.flypass.apirest.dtos.request.client.ClientRequestDTO;
import co.com.flypass.apirest.dtos.request.client.ClientUpdateRequestDTO;
import co.com.flypass.apirest.dtos.response.ClientResponseDTO;
import co.com.flypass.apirest.handlers.IClientHandler;
import co.com.flypass.apirest.mappers.IClientDTOMapper;
import co.com.flypass.ports.inbound.ClientUseCasePort;
import org.springframework.stereotype.Service;

@Service
public class ClientHandlerImpl implements IClientHandler {
    private final ClientUseCasePort clientUseCasePort;
    private final IClientDTOMapper clientDTOMapper;

    public ClientHandlerImpl(ClientUseCasePort clientUseCasePort, IClientDTOMapper clientDTOMapper) {
        this.clientUseCasePort = clientUseCasePort;
        this.clientDTOMapper = clientDTOMapper;
    }
    //todo: Agregar logica que no es del negocio necesaria para el buen funcionamiento. conversiones entre objetos dtos y modelo.
    @Override
    public ClientResponseDTO save(ClientRequestDTO clientRequestDTO) {
        return clientDTOMapper.toClientResponseDto(clientUseCasePort.save(clientDTOMapper.toClient(clientRequestDTO)));
    }

    @Override
    public ClientResponseDTO update(ClientUpdateRequestDTO clientUpdateRequestDTO) {
        return clientDTOMapper.toClientResponseDto(clientUseCasePort.save(clientDTOMapper.toClient(clientUpdateRequestDTO)));
    }

    @Override
    public void deleteClientById(Long clientId) {
        clientUseCasePort.deleteClientById(clientId);
    }
}
