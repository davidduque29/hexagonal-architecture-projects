package co.com.flypass.jpa.postgresql.adapters.transaction.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionConstants {
    public static final String TRANSACTION_CONSIGNMENT = "CONSIGNACION";
    public static final String TRANSACTION_WITHDRAWAL = "RETIRO";
    public static final String TRANSACTION_TRANSFER = "TRANSFERENCIA";
    public static final String ERR_NOT_FOUND_STATUS = "ERR_ACCOUNT_NOT_FOUND";
    public static final String ERR_NOT_FOUND_CODE = "404";
    public static final String ERR_ACCOUNT_NOT_FOUND_DESCRIPTION = "La cuenta de origen no existe en el sistema";
    public static final String ERR_INSUFFICIENT_MONEY_STATUS = "ERR_INSUFFICIENT_FUNDS";
    public static final String ERR_INSUFFICIENT_MONEY_DESCRIPTION = "Saldo insuficiente en la cuenta de origen";
    public static final String ERR_FORBIDDEN_CODE = "403";

}