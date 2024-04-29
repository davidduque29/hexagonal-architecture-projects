package co.com.flypass.jpa.postgresql.adapters.product.repository;

import co.com.flypass.jpa.postgresql.adapters.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductQueryRepository extends JpaRepository<ProductEntity, Long> {

    @Query(value = "SELECT p FROM ProductEntity p WHERE p.numeroCuenta = ?1")
    ProductEntity findCountNumberByCountNumber(String numerocuenta);

    @Query("SELECT COUNT(p) FROM ProductEntity p WHERE p.cliente.id = :clienteId")
    int countProductsByClient(@Param("clienteId") Long clienteId);

    @Query("SELECT COUNT(c) FROM CustomerEntity c WHERE c.id = :clienteId")
    int countExistingClientById(@Param("clienteId") Long clienteId);

}
