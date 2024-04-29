package co.com.flypass.jpa.postgresql.adapters.transfer.entity;


import co.com.flypass.jpa.postgresql.adapters.product.entity.ProductEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transferencias")
public class TransferEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transferencia")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cuenta_envio_id", nullable = false)
    private ProductEntity cuentaEnvio;

    @ManyToOne
    @JoinColumn(name = "cuenta_recepcion_id", nullable = false)
    private ProductEntity cuentaRecepcion;

    @Column(name = "monto_transferencia", columnDefinition = "DECIMAL(10, 2) COMMENT 'Monto de la transferencia'")
    private Double monto;

    @Column(name = "fecha_transferencia", nullable = false, columnDefinition = "TIMESTAMP COMMENT 'Fecha de la transferencia'")
    private Date fechaTransferencia;

    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoTransferencia estado; // Estado de la transferencia (completada, pendiente, cancelada, etc.)

    @Column(name = "numero_referencia", nullable = false, unique = true)
    private String numeroReferencia; // Número de referencia único para la transferencia

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    public enum EstadoTransferencia {
        COMPLETADA,
        PENDIENTE,
        CANCELADA,
    }

}