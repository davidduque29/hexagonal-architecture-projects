package co.com.flypass.apirest.controllers.customer.handlers;

import co.com.flypass.apirest.dtos.request.customer.CustomerRequestDTO;
import co.com.flypass.apirest.controllers.customer.response.CustomerResponse;
import co.com.flypass.apirest.dtos.response.CustomerResponseDTO;


public interface ICustomerHandler {
    CustomerResponseDTO add(CustomerRequestDTO customerRequestDTO);

    CustomerResponse updateCustomerRegisterId(CustomerRequestDTO customerInput);

    void deleteClientById(Long clientId);

    CustomerResponse listCustomers();
}
