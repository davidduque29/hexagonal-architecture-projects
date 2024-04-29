package co.com.flypass.jpa.postgresql.adapters.transfer.repository;

import co.com.flypass.jpa.postgresql.adapters.transfer.entity.TransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransferRepository extends JpaRepository<TransferEntity, Long> {

    @Query(value = "SELECT p FROM TransferEntity p WHERE p.id = ?1")
    TransferEntity findCountIdByCountId(Long id);
    boolean existsByNumeroReferencia(String numeroReferencia);

}
