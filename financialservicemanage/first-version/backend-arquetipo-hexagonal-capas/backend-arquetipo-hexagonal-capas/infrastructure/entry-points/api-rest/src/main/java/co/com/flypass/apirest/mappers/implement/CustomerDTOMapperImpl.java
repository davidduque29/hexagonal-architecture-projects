package co.com.flypass.apirest.mappers.implement;

import co.com.flypass.apirest.dtos.request.customer.CustomerRequestDTO;
import co.com.flypass.apirest.dtos.response.CustomerResponseDTO;
import co.com.flypass.apirest.mappers.ICustomerDTOMapper;
import co.com.flypass.models.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDTOMapperImpl implements ICustomerDTOMapper {

    @Override
    public Customer toCustomer(CustomerRequestDTO request) {
        if (request == null) {
            return null;
        }
        return Customer.builder()
                .id(request.getId())
                .tipoIdentificacion(request.getTipoIdentificacion())
                .numeroIdentificacion(request.getNumeroIdentificacion())
                .primerNombre(request.getPrimerNombre())
                .segundoNombre(request.getSegundoNombre())
                .primerApellido(request.getPrimerApellido())
                .segundoApellido(request.getSegundoApellido())
                .correoElectronico(request.getCorreoElectronico())
                .fechaNacimiento(request.getFechaNacimiento())
                .fechaModificacion(request.getFechaModificacion())
                .build();
        // customer.validCustomer();

    }

    @Override
    public CustomerResponseDTO toCustomerResponseDTO(Customer customer) {
        if (customer == null){
            return null;
        }
        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setId(customer.getId());
        customerResponseDTO.setTipoIdentificacion(customer.getTipoIdentificacion());
        customerResponseDTO.setNumeroIdentificacion(customer.getNumeroIdentificacion());
        customerResponseDTO.setPrimerNombre(customer.getPrimerNombre());
        customerResponseDTO.setSegundoNombre(customer.getSegundoNombre());
        customerResponseDTO.setPrimerApellido(customer.getPrimerApellido());
        customerResponseDTO.setSegundoApellido(customer.getSegundoApellido());
        customerResponseDTO.setCorreoElectronico(customer.getCorreoElectronico());
        customerResponseDTO.setFechaNacimiento(customer.getFechaNacimiento());
        customerResponseDTO.setFechaCreacion(customer.getFechaCreacion());
        customerResponseDTO.setFechaModificacion(customer.getFechaModificacion());
        return customerResponseDTO;
    }
}
