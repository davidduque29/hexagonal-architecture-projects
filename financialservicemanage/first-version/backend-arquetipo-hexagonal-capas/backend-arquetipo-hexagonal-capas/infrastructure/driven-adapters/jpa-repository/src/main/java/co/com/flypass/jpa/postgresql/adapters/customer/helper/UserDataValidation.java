package co.com.flypass.jpa.postgresql.adapters.customer.helper;

import co.com.flypass.jpa.postgresql.adapters.customer.entity.CustomerEntity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class UserDataValidation {
    public CustomerEntity formattedDate(CustomerEntity customerEntity) {
        CustomerEntity formattedCustomer  = new CustomerEntity();
        Date fechaNacimientoDate = customerEntity.getFechaNacimiento();
        // Convertir Date a LocalDate
        LocalDate fechaNacimiento = fechaNacimientoDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        formattedCustomer.setFechaNacimiento(Date.from(fechaNacimiento.atStartOfDay(ZoneId.systemDefault()).toInstant()));

        return formattedCustomer ;
    }
}
