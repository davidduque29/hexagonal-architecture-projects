package co.com.flypass.models;

import co.com.flypass.exception.BussinessException;
import co.com.flypass.exception.ExceptionUtil;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder(toBuilder = true)
public class Transfer {
    private Long idCuentaEnvio;
    private Long idCuentaDestino;
    private Double monto;
    private Date fechaTransferencia;
    private String estado;
    private String numeroReferencia;
    private String descripcion;



    public void validTransfer() {
        this.isNotNullOrEmpty();
    }

    private void isNotNullOrEmpty() {
        if (this.idCuentaEnvio == null ||  this.idCuentaDestino == null || this.monto == 0) {
            throw new BussinessException(ExceptionUtil.LOAD_NOT_ACCEPTABLE_EXCEPTION_STATUS
                    , ExceptionUtil.LOAD_NOT_ACCEPTABLE_EXCEPTION_DETAIL
                    , ExceptionUtil.LOAD_NOT_ACCEPTABLE_EXCEPTION_CODE);

        }

    }
}

