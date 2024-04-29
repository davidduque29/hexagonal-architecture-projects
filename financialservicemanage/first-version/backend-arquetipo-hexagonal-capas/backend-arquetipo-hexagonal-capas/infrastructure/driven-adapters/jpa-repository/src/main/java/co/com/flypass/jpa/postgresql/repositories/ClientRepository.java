package co.com.flypass.jpa.postgresql.repositories;


import co.com.flypass.jpa.postgresql.entities.ClientEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<ClientEntity, Long> {

    List<ClientEntity> findByIdentificationNumber(Long id);

}
