package co.com.flypass.apirest.controllers.product.request;

import lombok.*;

import java.util.Date;


@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String tipoCuenta;
    private String numeroCuenta;
    private String estado;
    private Double saldo;
    private boolean exentaGMF;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long clienteId;
}
