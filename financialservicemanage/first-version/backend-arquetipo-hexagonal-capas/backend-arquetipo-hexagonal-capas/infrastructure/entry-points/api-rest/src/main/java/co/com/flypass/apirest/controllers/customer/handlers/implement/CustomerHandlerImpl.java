package co.com.flypass.apirest.controllers.customer.handlers.implement;

import co.com.flypass.apirest.controllers.customer.handlers.ICustomerHandler;
import co.com.flypass.apirest.controllers.customer.helper.CustomerCreationHelper;
import co.com.flypass.apirest.dtos.request.customer.CustomerRequestDTO;
import co.com.flypass.apirest.controllers.customer.response.CustomerResponse;
import co.com.flypass.apirest.dtos.response.CustomerResponseDTO;
import co.com.flypass.apirest.mappers.IClientDTOMapper;
import co.com.flypass.apirest.mappers.ICustomerDTOMapper;
import co.com.flypass.models.Customer;
import co.com.flypass.ports.inbound.ClientUseCasePort;
import co.com.flypass.ports.inbound.CustomerUseCasePort;
import org.springframework.stereotype.Service;

@Service
public class CustomerHandlerImpl implements ICustomerHandler {
    private final CustomerUseCasePort customerUseCasePort;
    private final CustomerCreationHelper creationHelper;
    private final ICustomerDTOMapper customerDTOMapper;

    public CustomerHandlerImpl(
            CustomerUseCasePort customerUseCasePort, CustomerCreationHelper creationHelper
            , ICustomerDTOMapper customerDTOMapper) {
        this.customerUseCasePort = customerUseCasePort;
        this.creationHelper = creationHelper;
        this.customerDTOMapper = customerDTOMapper;
    }
    //todo: Agregar logica que no es del negocio necesaria para el buen funcionamiento. conversiones entre objetos dtos y modelo.


    @Override
    public CustomerResponseDTO add(CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerUseCasePort.createCustomer(customerDTOMapper.toCustomer(customerRequestDTO));
        return customerDTOMapper.toCustomerResponseDTO(customer);
    }

    @Override
    public CustomerResponse updateCustomerRegisterId(CustomerRequestDTO customerInput) {
        Customer customer = customerUseCasePort.updateCustomerId(creationHelper
                .convertRequestToCustomer(customerInput));
        return creationHelper.createResponse(customer);
    }

    @Override
    public void deleteClientById(Long clientId) {
        customerUseCasePort.deleteCustomerById(clientId);
    }

    @Override
    public CustomerResponse listCustomers() {
        return null;
    }

}
