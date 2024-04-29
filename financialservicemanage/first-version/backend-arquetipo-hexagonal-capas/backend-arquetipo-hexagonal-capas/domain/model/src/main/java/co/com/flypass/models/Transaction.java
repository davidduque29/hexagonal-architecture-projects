package co.com.flypass.models;

import co.com.flypass.exception.BussinessException;
import co.com.flypass.exception.ExceptionUtil;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder(toBuilder = true)
public class Transaction {
    private String tipo; // Tipo de transacción: Consignación, Retiro, Transferencia
    private double monto;
    private Date fechaTransaccion;
    private Long cuentaId;


    public void validTransaction() {
        this.isNotNullOrEmpty();
    }

    private void isNotNullOrEmpty() {
        if (this.tipo == null ||  this.tipo.isBlank() || this.monto == 0) {
            throw new BussinessException(ExceptionUtil.LOAD_NOT_ACCEPTABLE_EXCEPTION_STATUS
                    , ExceptionUtil.LOAD_NOT_ACCEPTABLE_EXCEPTION_DETAIL
                    , ExceptionUtil.LOAD_NOT_ACCEPTABLE_EXCEPTION_CODE);

        }

    }
}

