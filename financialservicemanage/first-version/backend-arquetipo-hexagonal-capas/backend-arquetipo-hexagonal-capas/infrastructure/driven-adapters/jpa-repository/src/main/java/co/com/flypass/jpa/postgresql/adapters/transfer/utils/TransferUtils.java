package co.com.flypass.jpa.postgresql.adapters.transfer.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransferUtils {

    private static final String CUENTA_CORRIENTE_PREFIX = "33";
    private static final String CUENTA_AHORROS_PREFIX = "53";
    private static final int NUM_DIGITS = 8; // Número de dígitos generados aleatoriamente
    private static final Random random = new Random();

    public static String generateReferenceNumber(boolean isCuentaCorriente) {
        String prefix = isCuentaCorriente ? "TRF-" + CUENTA_CORRIENTE_PREFIX + "-" : "TRF-" + CUENTA_AHORROS_PREFIX + "-";
        StringBuilder sb = new StringBuilder(prefix);
        for (int i = 0; i < NUM_DIGITS; i++) {
            sb.append(random.nextInt(10)); // Generar números aleatorios utilizando el objeto Random reutilizado
        }
        return sb.toString();
    }
}