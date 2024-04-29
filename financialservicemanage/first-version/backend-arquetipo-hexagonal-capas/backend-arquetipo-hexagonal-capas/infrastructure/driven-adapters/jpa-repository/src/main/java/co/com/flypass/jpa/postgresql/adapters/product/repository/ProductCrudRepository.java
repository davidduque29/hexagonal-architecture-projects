package co.com.flypass.jpa.postgresql.adapters.product.repository;

import co.com.flypass.jpa.postgresql.adapters.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ProductCrudRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findProductById(Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE productos SET saldo = saldo - :monto WHERE id_producto = :productId", nativeQuery = true)
    void updateAccountBalanceById(@Param("productId") Long productId, @Param("monto") Double monto);

    @Query(value = "SELECT p FROM ProductEntity p WHERE p.numeroCuenta = ?1")
    ProductEntity findCountNumberByCountNumber(String numerocuenta);

    @Query("SELECT COUNT(p) FROM ProductEntity p WHERE p.cliente.id = :clienteId")
    int countProductsByClient(@Param("clienteId") Long clienteId);

    @Query("SELECT COUNT(c) FROM CustomerEntity c WHERE c.id = :clienteId")
    int countExistingClientById(@Param("clienteId") Long clienteId);
}
