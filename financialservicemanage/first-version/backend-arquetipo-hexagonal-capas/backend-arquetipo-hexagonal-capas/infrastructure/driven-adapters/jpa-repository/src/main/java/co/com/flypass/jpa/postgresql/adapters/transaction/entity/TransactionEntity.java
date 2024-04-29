package co.com.flypass.jpa.postgresql.adapters.transaction.entity;

import co.com.flypass.jpa.postgresql.adapters.product.entity.ProductEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transacciones")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Transaccion")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Tipo de transacción'")
    private TipoTransaccion tipo;

    @Column(name = "monto", columnDefinition = "DECIMAL(10, 2) COMMENT 'Monto'")
    private double monto;

    @Column(name = "fecha_transaccion", nullable = false, columnDefinition = "TIMESTAMP COMMENT 'Fecha de transacción'")
    @CreationTimestamp
    private Date fechaTransaccion;

    @ManyToOne
    @JoinColumn(name = "cuenta_id", nullable = false)
    private ProductEntity cuenta;

    public enum TipoTransaccion {
        CONSIGNACION,
        RETIRO,
        TRANSFERENCIA
    }
}