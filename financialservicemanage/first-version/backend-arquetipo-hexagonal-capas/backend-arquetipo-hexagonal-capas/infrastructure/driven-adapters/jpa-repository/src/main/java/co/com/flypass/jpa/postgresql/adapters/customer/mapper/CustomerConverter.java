package co.com.flypass.jpa.postgresql.adapters.customer.mapper;


import co.com.flypass.exception.BussinessException;
import co.com.flypass.jpa.postgresql.adapters.customer.entity.CustomerEntity;
import co.com.flypass.models.Customer;
import co.com.flypass.jpa.postgresql.adapters.customer.mapper.impl.ICustomerMapper;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerConverter implements ICustomerMapper{

    @Override
    public CustomerEntity convertCustomerToEntityBuilder(Customer customer) {
        return CustomerEntity.builder()
                .tipoIdentificacion(customer.getTipoIdentificacion())
                .numeroIdentificacion(customer.getNumeroIdentificacion())
                .primerNombre(customer.getPrimerNombre())
                .segundoNombre(customer.getSegundoNombre())
                .primerApellido(customer.getPrimerApellido())
                .segundoApellido(customer.getSegundoApellido())
                .correoElectronico(customer.getCorreoElectronico())
                .fechaNacimiento(customer.getFechaNacimiento())
                .fechaCreacion(customer.getFechaCreacion())
                .fechaModificacion(customer.getFechaModificacion())
                .build();

    }

    @Override
    public CustomerEntity convertCustomerToEntity(Customer customerDto) {
        CustomerEntity entity = new CustomerEntity();
        entity.setId(customerDto.getId());
        entity.setTipoIdentificacion(customerDto.getTipoIdentificacion());
        entity.setNumeroIdentificacion(customerDto.getNumeroIdentificacion());
        entity.setPrimerNombre(customerDto.getPrimerNombre());
        entity.setSegundoNombre(customerDto.getSegundoNombre());
        entity.setPrimerApellido(customerDto.getPrimerApellido());
        entity.setSegundoApellido(customerDto.getSegundoApellido());
        entity.setCorreoElectronico(customerDto.getCorreoElectronico());
        entity.setFechaNacimiento(customerDto.getFechaNacimiento());
        entity.setFechaCreacion(customerDto.getFechaCreacion());
        entity.setFechaModificacion(customerDto.getFechaModificacion());
        return entity;
    }

    @Override
    public CustomerEntity convertCustomerToEntityOptional(Optional<Customer> customerDto) {
        if (customerDto.isEmpty()) {
            throw new BussinessException("NOT_FOUND", "Customer Optional is empty", "404");
        }

        CustomerEntity entity = new CustomerEntity();
        Customer customer = customerDto.get();

        entity.setId(customer.getId());
        entity.setTipoIdentificacion(customer.getTipoIdentificacion());
        entity.setNumeroIdentificacion(customer.getNumeroIdentificacion());
        entity.setPrimerNombre(customer.getPrimerNombre());
        entity.setSegundoNombre(customer.getSegundoNombre());
        entity.setPrimerApellido(customer.getPrimerApellido());
        entity.setSegundoApellido(customer.getSegundoApellido());
        entity.setCorreoElectronico(customer.getCorreoElectronico());
        entity.setFechaNacimiento(customer.getFechaNacimiento());
        entity.setFechaCreacion(customer.getFechaCreacion());
        entity.setFechaModificacion(customer.getFechaModificacion());

        return entity;
    }

    @Override
    public Customer convertEntityToCostumer(CustomerEntity entity) {
        if (entity == null){
            return null;
        }
        return Customer.builder()
                .id(entity.getId())
                .tipoIdentificacion(entity.getTipoIdentificacion())
                .numeroIdentificacion(entity.getNumeroIdentificacion())
                .primerNombre(entity.getPrimerNombre())
                .segundoNombre(entity.getSegundoNombre())
                .primerApellido(entity.getPrimerApellido())
                .segundoApellido(entity.getSegundoApellido())
                .correoElectronico(entity.getCorreoElectronico())
                .fechaNacimiento(entity.getFechaNacimiento())
                .fechaCreacion(entity.getFechaCreacion())
                .fechaModificacion(entity.getFechaModificacion())
                .build();
    }

    @Override
    public Customer convertEntityToCostumerOptional(Optional<CustomerEntity> entity) {
        return Customer.builder()
                .id(entity.get().getId())
                .tipoIdentificacion(entity.get().getTipoIdentificacion())
                .numeroIdentificacion(entity.get().getNumeroIdentificacion())
                .primerNombre(entity.get().getPrimerNombre())
                .segundoNombre(entity.get().getSegundoNombre())
                .primerApellido(entity.get().getPrimerApellido())
                .segundoApellido(entity.get().getSegundoApellido())
                .correoElectronico(entity.get().getCorreoElectronico())
                .fechaNacimiento(entity.get().getFechaNacimiento())
                .fechaCreacion(entity.get().getFechaCreacion())
                .fechaModificacion(entity.get().getFechaModificacion())
                .build();
    }

    @Override
    public List<CustomerEntity> convertToEntityList(List<Customer> customerDtos) {
        List<CustomerEntity> entities = new ArrayList<>();
        for (Customer customerDto : customerDtos) {
            entities.add(convertCustomerToEntity(customerDto));
        }
        return entities;
    }

    @Override
    public List<Customer> convertListEntityToCustomerList(List<CustomerEntity> entities) {
        List<Customer> customerDtos = new ArrayList<>();
        for (CustomerEntity entity : entities) {
            customerDtos.add(convertEntityToCostumer(entity));
        }
        return customerDtos;
    }

    @Override
    public List<Optional<CustomerEntity>> convertToEntityOptionalList(List<Optional<Customer>> optionalcustomerDtos) {
        List<Optional<CustomerEntity>> optionalEntities = new ArrayList<>();
        CustomerConverter converter = new CustomerConverter(); // Creamos una instancia de CustomerConverter
        for (Optional<Customer> optionalcustomerDto : optionalcustomerDtos) {
            optionalEntities.add(optionalcustomerDto.map(converter::convertCustomerToEntity)); // Usamos la instancia para llamar al método no estático
        }
        return optionalEntities;
    }

    @Override
    public List<Optional<Customer>> convertTocustomerDtoOptionalList(List<Optional<CustomerEntity>> optionalEntities) {
        List<Optional<Customer>> optionalcustomerDtos = new ArrayList<>();
        CustomerConverter converter = new CustomerConverter(); // Creamos una instancia de CustomerConverter
        for (Optional<CustomerEntity> optionalEntity : optionalEntities) {
            optionalcustomerDtos.add(optionalEntity.map(converter::convertEntityToCostumer)); // Usamos la instancia para llamar al método no estático
        }
        return optionalcustomerDtos;
    }
}
