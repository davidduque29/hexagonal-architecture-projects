package co.com.flypass.jpa.postgresql.adapters.transfer.repository;

import co.com.flypass.jpa.postgresql.adapters.transfer.entity.TransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransferCrudRepository extends JpaRepository<TransferEntity, Long> {
    Optional<TransferEntity> findTransferById(Long id);

}
