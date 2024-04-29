package co.com.flypass.apirest.handlers;

import co.com.flypass.apirest.dtos.request.client.ClientRequestDTO;
import co.com.flypass.apirest.dtos.request.client.ClientUpdateRequestDTO;
import co.com.flypass.apirest.dtos.response.ClientResponseDTO;

public interface IClientHandler {
    ClientResponseDTO save(ClientRequestDTO clientRequestDTO);

    ClientResponseDTO update(ClientUpdateRequestDTO clientUpdateRequestDTO);
    void deleteClientById(Long clientId);

}
