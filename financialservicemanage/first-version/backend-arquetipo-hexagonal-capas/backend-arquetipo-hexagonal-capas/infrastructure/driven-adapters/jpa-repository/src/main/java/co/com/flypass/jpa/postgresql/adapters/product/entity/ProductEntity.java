package co.com.flypass.jpa.postgresql.adapters.product.entity;


import co.com.flypass.jpa.postgresql.adapters.customer.entity.CustomerEntity;
import co.com.flypass.jpa.postgresql.adapters.transaction.entity.TransactionEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productos")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cuenta", nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Tipo de cuenta'")
    public TipoCuenta tipoCuenta;

    @Column(name = "numero_cuenta", nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Número de cuenta'")
    private String numeroCuenta;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", columnDefinition = "VARCHAR(255) COMMENT 'Estado de cuenta'")
    public EstadoCuenta estado;

    @Column(name = "saldo", columnDefinition = "DECIMAL(10, 2) COMMENT 'Saldo'")
    private double saldo;

    @Column(name = "exenta_gmf", columnDefinition = "BIT COMMENT 'Exenta de GMF'")
    private boolean exentaGMF;

    @Column(name = "fecha_creacion", nullable = false, columnDefinition = "TIMESTAMP COMMENT 'Fecha de creación'")
    @CreationTimestamp
    private Date fechaCreacion;

    @Column(name = "fecha_modificacion", columnDefinition = "TIMESTAMP COMMENT 'Fecha de modificación'")
    private Date fechaModificacion;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private CustomerEntity cliente;

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL)
    private List<TransactionEntity> transacciones;

    public enum TipoCuenta {
        CUENTA_CORRIENTE,
        CUENTA_DE_AHORROS
    }

    public enum EstadoCuenta {
        ACTIVA,
        INACTIVA,
        CANCELADA
    }

    public void setCliente(CustomerEntity cliente) {
        this.cliente = cliente;
    }
}