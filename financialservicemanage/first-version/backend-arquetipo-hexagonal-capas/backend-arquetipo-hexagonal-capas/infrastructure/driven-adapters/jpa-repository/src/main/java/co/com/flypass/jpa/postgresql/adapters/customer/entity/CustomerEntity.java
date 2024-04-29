package co.com.flypass.jpa.postgresql.adapters.customer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clientes")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tipo_identificacion", nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Tipo de identificación'")
    private String tipoIdentificacion;

    @Column(name = "numero_identificacion", nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Número de identificación'")
    private String numeroIdentificacion;

    @Column(name = "primer_nombre", nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Primer nombre'")
    //@Size(min = 2)
    private String primerNombre;

    @Column(name = "segundo_nombre", nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Segundo nombre'")
    //@Size(min = 2)
    private String segundoNombre;

    @Column(name = "primer_apellido", nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Primer apellido'")
    //@Size(min = 2)
    private String primerApellido;

    @Column(name = "segundo_apellido", nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Segundo apellido'")
    //@Size(min = 2)
    private String segundoApellido;

    @Column(name = "correo_electronico", columnDefinition = "VARCHAR(255) COMMENT 'Correo electrónico'")
    //@Email
    private String correoElectronico;

    @Column(name = "fecha_nacimiento", columnDefinition = "DATE COMMENT 'Fecha de nacimiento'")
    private Date fechaNacimiento;

    @Column(name = "fecha_creacion", nullable = false, columnDefinition = "TIMESTAMP COMMENT 'Fecha de creación'")
    @CreationTimestamp
    private Date fechaCreacion;

    @Column(name = "fecha_modificacion", columnDefinition = "TIMESTAMP COMMENT 'Fecha de modificación'")
    private Date fechaModificacion;

}