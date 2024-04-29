package co.com.flypass.jpa.postgresql.adapters.customer.repository;


import co.com.flypass.jpa.postgresql.adapters.customer.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserQueryRepository extends JpaRepository<CustomerEntity, Long> {

    @Query(value = "SELECT numero_identificacion FROM customerfinancedata.clientes WHERE numero_identificacion = ?1"
            , nativeQuery = true)
    String findNumberIdentificationByNumberIdentification(String identification);
}
