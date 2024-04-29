package co.com.flypass.jpa.postgresql.adapters.product.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductConstants {
    public static final String SAVINGS_ACCOUNT_TYPE = "CUENTA_DE_AHORROS";
    public static final String CURRENT_ACCOUNT_TYPE = "CUENTA_CORRIENTE";
    public static final String ACTIVE_ACCOUNT = "ACTIVA";
    public static final String INACTIVE_ACCOUNT = "INACTIVA";
    public static final String CANCELED_ACCOUNT = "CANCELADA";
}