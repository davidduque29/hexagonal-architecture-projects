package co.com.flypass.jpa.postgresql.adapters.customer.mapper.impl;

import co.com.flypass.exception.BussinessException;
import co.com.flypass.exception.ExceptionUtil;
import co.com.flypass.jpa.postgresql.adapters.customer.ICustomerAdapterFlow;
import co.com.flypass.jpa.postgresql.adapters.customer.entity.CustomerEntity;
import co.com.flypass.jpa.postgresql.adapters.customer.helper.UserDataValidation;
import co.com.flypass.jpa.postgresql.adapters.customer.mapper.CustomerConverter;
import co.com.flypass.jpa.postgresql.adapters.customer.repository.CustomerCrudRepository;
import co.com.flypass.jpa.postgresql.adapters.customer.repository.UserQueryRepository;
import co.com.flypass.jpa.postgresql.adapters.product.repository.ProductQueryRepository;
import co.com.flypass.models.Customer;


import java.util.Date;
import java.util.Optional;


public class CustomerAdapterFlow implements ICustomerAdapterFlow {
    private final CustomerCrudRepository customerCrudRepository;
    private final ProductQueryRepository productQueryRepository;

    public CustomerAdapterFlow(CustomerCrudRepository customerCrudRepository, UserQueryRepository userQueryRepository, ProductQueryRepository productQueryRepository) {
        this.customerCrudRepository = customerCrudRepository;
        this.productQueryRepository = productQueryRepository;
    }

    public CustomerEntity createCustomer(Customer customerModel) {
        validateUserExistence(customerModel.getNumeroIdentificacion());
        CustomerEntity customerEntity = convertCustomerModelToEntity(customerModel);
        return customerCrudRepository.save(completeCustomerData(customerEntity));
    }

    public CustomerEntity convertCustomerModelToEntity(Customer customerModel) {
        var customerEntityData = transformModelTOEntity(customerModel);
        return completeCustomerData(customerEntityData);
    }

    public CustomerEntity transformModelTOEntity(Customer customerModel) {
        var customerEntityData = new CustomerEntity();
        CustomerConverter customerData = new CustomerConverter();
        customerEntityData = customerData.convertCustomerToEntityBuilder(customerModel);
        return customerEntityData;
    }

    public CustomerEntity completeCustomerData(CustomerEntity customerEntity) {
        Date fechaActual = new Date();
        UserDataValidation formatearFechaNacimiento = new UserDataValidation();
        customerEntity.setFechaNacimiento(formatearFechaNacimiento.formattedDate(customerEntity).getFechaNacimiento());
        customerEntity.setFechaModificacion(fechaActual);
        return customerEntity;
    }

    public boolean validateUserExistence(String numeroIdentificacion) throws BussinessException {
        if (userExists(numeroIdentificacion)) {
            throw new BussinessException(ExceptionUtil.EXISTING_USER_EXCEPTION_STATUS,
                    ExceptionUtil.EXISTING_USER_EXCEPTION_DETAIL,
                    ExceptionUtil.EXISTING_USER_EXCEPTION_CODE);
        }
        return true;
    }

    public boolean userExists(String numeroIdentificacion) {
        return findUserExistence(numeroIdentificacion);
    }

    public boolean findUserExistence(String identification) {
        CustomerEntity existingUser = customerCrudRepository.findByNumeroIdentificacion(identification);
        return existingUser != null;
    }
  /*  public String findByNumberIdentification(String identification) {
        return   Optional.ofNullable(customerCrudRepository.findByNumeroIdentificacion(identification))
                .map(CustomerEntity::getNumeroIdentificacion)
                .orElseGet(null);

    }*/


    public CustomerEntity deleteCustomer(Long id) {
        CustomerEntity customerToDelete = customerCrudRepository.findById(id)
                .orElseThrow(() -> new BussinessException("CLIENTE_NO_EXISTE",
                        "El cliente con ID " + id + " no existe",
                        "404"));

        int productCount = productQueryRepository.countProductsByClient(customerToDelete.getId());
        if (productCount > 0) {
            throw new BussinessException("PRODUCTOS_ASOCIADOS",
                    "No se puede eliminar el cliente debido a productos asociados",
                    "400");
        }

        customerCrudRepository.delete(customerToDelete);
        return customerToDelete;
    }
    public CustomerEntity updateCustomerId(Long usuarioId, Customer customerModel) {
        return customerCrudRepository.findById(usuarioId).map(customer -> {
            customer.setTipoIdentificacion(customerModel.getTipoIdentificacion());
            customer.setNumeroIdentificacion(customerModel.getNumeroIdentificacion());
            customer.setPrimerNombre(customerModel.getPrimerNombre());
            customer.setSegundoNombre(customerModel.getSegundoNombre());
            customer.setPrimerApellido(customerModel.getPrimerApellido());
            customer.setSegundoApellido(customerModel.getSegundoApellido());
            customer.setCorreoElectronico(customerModel.getCorreoElectronico());
            customer.setFechaNacimiento(customerModel.getFechaNacimiento());
            customer.setFechaModificacion(new Date());
            return customerCrudRepository.save(customer);
        }).orElseThrow(() -> new BussinessException("CLIENTE_NO_EXISTE", "El cliente con ID " + usuarioId + " no existe", "404"));
    }
}
