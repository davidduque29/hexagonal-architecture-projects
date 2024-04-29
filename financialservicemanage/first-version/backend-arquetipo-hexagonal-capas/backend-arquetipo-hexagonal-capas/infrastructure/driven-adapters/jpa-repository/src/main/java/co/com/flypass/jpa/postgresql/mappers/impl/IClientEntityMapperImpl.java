package co.com.flypass.jpa.postgresql.mappers.impl;

import co.com.flypass.jpa.postgresql.entities.ClientEntity;
import co.com.flypass.jpa.postgresql.mappers.IClientEntityMapper;
import co.com.flypass.models.Client;
import org.springframework.stereotype.Service;

@Service
public class IClientEntityMapperImpl implements IClientEntityMapper {
    @Override
    public Client toClient(ClientEntity clientEntity) {
        if (clientEntity == null){
            return null;
        }
        return Client.builder()
                .id(clientEntity.getId())
                .identificationType(clientEntity.getIdentificationType())
                .identificationNumber(clientEntity.getIdentificationNumber())
                .firstName(clientEntity.getFirstName())
                .lastName(clientEntity.getLastName())
                .emailAddress(clientEntity.getEmailAddress())
                .dateOfBirth(clientEntity.getDateOfBirth())
                .creationDate(clientEntity.getCreationDate())
                .modificationDate(clientEntity.getModificationDate())
                .build();
    }

    @Override
    public ClientEntity toClientEntity(Client client) {
        if (client == null){
            return null;
        }
        ClientEntity clientEntity;
        clientEntity = new ClientEntity();
        clientEntity.setId(client.getId());
        clientEntity.setIdentificationType(client.getIdentificationType());
        clientEntity.setIdentificationNumber(client.getIdentificationNumber());
        clientEntity.setFirstName(client.getFirstName());
        clientEntity.setLastName(client.getLastName());
        clientEntity.setEmailAddress(client.getEmailAddress());
        clientEntity.setDateOfBirth(client.getDateOfBirth());
        clientEntity.setCreationDate(client.getCreationDate());
        clientEntity.setModificationDate(client.getModificationDate());
        return clientEntity;
    }
}
