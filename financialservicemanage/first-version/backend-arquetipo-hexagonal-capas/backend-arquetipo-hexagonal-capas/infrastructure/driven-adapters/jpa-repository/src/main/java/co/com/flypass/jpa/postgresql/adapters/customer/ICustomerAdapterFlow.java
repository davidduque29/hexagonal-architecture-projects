package co.com.flypass.jpa.postgresql.adapters.customer;

import co.com.flypass.exception.BussinessException;
import co.com.flypass.jpa.postgresql.adapters.customer.entity.CustomerEntity;
import co.com.flypass.models.Customer;

public interface ICustomerAdapterFlow {
    CustomerEntity createCustomer(Customer customerModel);

    CustomerEntity convertCustomerModelToEntity(Customer customerModel);

    CustomerEntity transformModelTOEntity(Customer customerModel);

    CustomerEntity completeCustomerData(CustomerEntity customerEntity);

    boolean validateUserExistence(String numeroIdentificacion) throws BussinessException;

    boolean userExists(String numeroIdentificacion);

    boolean findUserExistence(String identification);

    CustomerEntity deleteCustomer(Long id);

    CustomerEntity updateCustomerId(Long usuarioId, Customer customerModel);
}
