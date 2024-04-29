package co.com.flypass.apirest.mappers;

import co.com.flypass.apirest.dtos.request.client.ClientRequestDTO;
import co.com.flypass.apirest.dtos.request.client.ClientUpdateRequestDTO;
import co.com.flypass.apirest.dtos.response.ClientResponseDTO;
import co.com.flypass.models.Client;

public interface IClientDTOMapper {
    Client toClient(ClientRequestDTO newUserRequestDTO);
    Client toClient(ClientUpdateRequestDTO updateUserRequestDTO);
    ClientResponseDTO toClientResponseDto(Client client);
}
