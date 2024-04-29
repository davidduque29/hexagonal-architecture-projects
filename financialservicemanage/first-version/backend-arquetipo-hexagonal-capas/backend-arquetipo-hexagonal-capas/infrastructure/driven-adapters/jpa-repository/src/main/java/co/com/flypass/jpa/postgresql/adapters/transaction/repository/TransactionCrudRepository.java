package co.com.flypass.jpa.postgresql.adapters.transaction.repository;


import co.com.flypass.jpa.postgresql.adapters.transaction.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TransactionCrudRepository extends JpaRepository<TransactionEntity, Long> {
    Optional<TransactionEntity> findTransactionById(Long id);

    @Query("SELECT SUM(t.monto) FROM TransactionEntity t WHERE t.cuenta.id = :productId")
    Double getBalanceByProductId(@Param("productId") Long productId);

}
