package co.com.flypass.apirest.controllers.transfer.request;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequest {
    private Long idCuentaEnvio;
    private Long idCuentaRecepcion;
    private Double monto;
    private String descripcion;
}
