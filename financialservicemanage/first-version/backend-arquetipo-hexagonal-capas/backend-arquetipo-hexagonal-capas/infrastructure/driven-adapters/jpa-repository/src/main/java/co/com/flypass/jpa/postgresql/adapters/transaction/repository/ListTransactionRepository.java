package co.com.flypass.jpa.postgresql.adapters.transaction.repository;


import co.com.flypass.jpa.postgresql.adapters.transaction.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ListTransactionRepository extends JpaRepository<TransactionEntity, Long> {

    @Query(value = "SELECT p FROM TransactionEntity p WHERE p.id = ?1")
    TransactionEntity findCountIdByCountId(Long id);


}
