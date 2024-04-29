package co.com.flypass.usecase.helper;

import co.com.flypass.models.Customer;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class UserDataValidation {
    public Customer formattedDate(Customer customer) {
        Customer formattedCustomer  = Customer.builder().build();
        Date fechaNacimientoDate = customer.getFechaNacimiento();
        // Convertir Date a LocalDate
        LocalDate fechaNacimiento = fechaNacimientoDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        formattedCustomer.setFechaNacimiento(Date.from(fechaNacimiento.atStartOfDay(ZoneId.systemDefault()).toInstant()));

        return formattedCustomer ;
    }
}
