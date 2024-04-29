package co.com.flypass.models;


import co.com.flypass.exception.BussinessException;
import co.com.flypass.exception.ExceptionUtil;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder(toBuilder = true)
public class Product {
    private String tipoCuenta;
    private String numeroCuenta;
    private String estado;
    private Double saldo;
    private boolean exentaGMF;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long clienteId;

    public void validProduct() {
        this.isNotNullOrEmpty();
    }

    private void isNotNullOrEmpty() {
        if (this.tipoCuenta == null || this.tipoCuenta.isBlank()
                ||saldo == null || saldo.isNaN()  )  {
            throw new BussinessException(ExceptionUtil.LOAD_NOT_ACCEPTABLE_EXCEPTION_STATUS
                    , ExceptionUtil.LOAD_NOT_ACCEPTABLE_EXCEPTION_DETAIL
                    , ExceptionUtil.LOAD_NOT_ACCEPTABLE_EXCEPTION_CODE);

        }

    }


}
