package co.com.flypass.apirest.mappers.implement;

import co.com.flypass.apirest.dtos.request.client.ClientRequestDTO;
import co.com.flypass.apirest.dtos.request.client.ClientUpdateRequestDTO;
import co.com.flypass.apirest.dtos.response.ClientResponseDTO;
import co.com.flypass.apirest.mappers.IClientDTOMapper;
import co.com.flypass.models.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientDTOMapperImpl implements IClientDTOMapper {
    @Override
    public Client toClient(ClientRequestDTO newUserRequestDTO) {
        if (newUserRequestDTO == null){
            return null;
        }
        return Client.builder()
                .identificationType(newUserRequestDTO.getIdentificationType())
                .identificationNumber(newUserRequestDTO.getIdentificationNumber())
                .firstName(newUserRequestDTO.getFirstName())
                .lastName(newUserRequestDTO.getLastName())
                .emailAddress(newUserRequestDTO.getEmailAddress())
                .dateOfBirth(newUserRequestDTO.getDateOfBirth())
                .creationDate(newUserRequestDTO.getCreationDate())
                .modificationDate(newUserRequestDTO.getModificationDate())
                .build();
    }

    @Override
    public Client toClient(ClientUpdateRequestDTO updateUserRequestDTO) {
        if (updateUserRequestDTO == null){
            return null;
        }
        return Client.builder()
                .id(updateUserRequestDTO.getId())
                .identificationType(updateUserRequestDTO.getIdentificationType())
                .identificationNumber(updateUserRequestDTO.getIdentificationNumber())
                .firstName(updateUserRequestDTO.getFirstName())
                .lastName(updateUserRequestDTO.getLastName())
                .emailAddress(updateUserRequestDTO.getEmailAddress())
                .dateOfBirth(updateUserRequestDTO.getDateOfBirth())
                .creationDate(updateUserRequestDTO.getCreationDate())
                .modificationDate(updateUserRequestDTO.getModificationDate())
                .build();
    }

    @Override
    public ClientResponseDTO toClientResponseDto(Client client) {
        if (client == null){
            return null;
        }
        ClientResponseDTO clientResponseDTO;
        clientResponseDTO = new ClientResponseDTO();
        clientResponseDTO.setId(client.getId());
        clientResponseDTO.setIdentificationType(client.getIdentificationType());
        clientResponseDTO.setIdentificationNumber(client.getIdentificationNumber());
        clientResponseDTO.setFirstName(client.getFirstName());
        clientResponseDTO.setLastName(client.getLastName());
        clientResponseDTO.setEmailAddress(client.getEmailAddress());
        clientResponseDTO.setDateOfBirth(client.getDateOfBirth());
        clientResponseDTO.setCreationDate(client.getCreationDate());
        clientResponseDTO.setModificationDate(client.getModificationDate());
        return clientResponseDTO;
    }
}
