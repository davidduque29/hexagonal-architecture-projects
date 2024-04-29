package co.com.flypass.apirest.mappers;

import co.com.flypass.apirest.dtos.request.customer.CustomerRequestDTO;
import co.com.flypass.apirest.dtos.response.CustomerResponseDTO;
import co.com.flypass.models.Customer;

public interface ICustomerDTOMapper {
    Customer toCustomer(CustomerRequestDTO request);
    CustomerResponseDTO toCustomerResponseDTO(Customer customer);
}
