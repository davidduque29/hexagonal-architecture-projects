package co.com.flypass.jpa.postgresql.adapters.product.utils;

import java.util.Random;

public class ProductUtils {

    private static final String CUENTA_CORRIENTE_PREFIX = "33";
    private static final String CUENTA_AHORROS_PREFIX = "53";
    private static final int NUM_DIGITS = 8; // Número de dígitos generados aleatoriamente
    private static final Random random = new Random();

    public static String generateAccountNumber(boolean isCuentaCorriente) {
        String prefix = isCuentaCorriente ? CUENTA_CORRIENTE_PREFIX : CUENTA_AHORROS_PREFIX;
        StringBuilder sb = new StringBuilder(prefix);
        for (int i = 0; i < NUM_DIGITS; i++) {
            sb.append(random.nextInt(10)); // Generar números aleatorios
        }
        return sb.toString();
    }
}