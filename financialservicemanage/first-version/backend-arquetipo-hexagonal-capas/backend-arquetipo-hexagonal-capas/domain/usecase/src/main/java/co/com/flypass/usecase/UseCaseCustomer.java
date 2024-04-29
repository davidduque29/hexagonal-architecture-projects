package co.com.flypass.usecase;


import co.com.flypass.exception.BussinessException;
import co.com.flypass.exception.ExceptionUtil;
import co.com.flypass.models.Customer;
import co.com.flypass.ports.outbound.CustomerGatewayPort;
import co.com.flypass.ports.inbound.CustomerUseCasePort;
import co.com.flypass.ports.outbound.ProductGatewayPort;
import co.com.flypass.usecase.helper.UserDataValidation;

import java.util.Date;
import java.util.List;



public class UseCaseCustomer implements CustomerUseCasePort {
    private final CustomerGatewayPort customerGatewayPort;
    private final ProductGatewayPort productGatewayPort;

    public UseCaseCustomer(CustomerGatewayPort customerGatewayPort, ProductGatewayPort productGatewayPort) {
        this.customerGatewayPort = customerGatewayPort;
        this.productGatewayPort = productGatewayPort;
    }

    //  CASO DE USO SON REGLAS DE NEGOCIO A NIVEL APLICACION
    //MODELO SON REGLAS DE NEGOCIO A NIVEL ORGANIZACION -QUE Y DEFINCION TECNOLOGIA - COMO
    @Override
    public Customer createCustomer(Customer customer) {
        customer.validCustomer();
        validateUserExistence(customer.getNumeroIdentificacion());
        return customerGatewayPort.createCustomer(completeCustomerData(customer));
    }

    public boolean userExists(String numeroIdentificacion) {
        return findUserExistence(numeroIdentificacion);
    }

    private boolean findUserExistence(String identification) {
        Customer existingUser = customerGatewayPort.findByNumberIdentification(identification);
        return existingUser != null;
    }

    private boolean validateUserExistence(String numeroIdentificacion) throws BussinessException {
        if (userExists(numeroIdentificacion)) {
            throw new BussinessException(ExceptionUtil.EXISTING_USER_EXCEPTION_STATUS,
                    ExceptionUtil.EXISTING_USER_EXCEPTION_DETAIL,
                    ExceptionUtil.EXISTING_USER_EXCEPTION_CODE);
        }
        return true;
    }

    private Customer completeCustomerData(Customer customer) {
        Date fechaActual = new Date();
        UserDataValidation formatearFechaNacimiento = new UserDataValidation();
        customer.setFechaNacimiento(formatearFechaNacimiento.formattedDate(customer).getFechaNacimiento());
        customer.setFechaModificacion(fechaActual);
        return customer;
    }

    @Override
    public Customer updateCustomerId(Customer customer) {
        Customer existingCustomer = findCustomerById(customer.getId());
        return customerGatewayPort.updateCustomerId(updateCustomerFields(existingCustomer, customer));
    }

    private Customer findCustomerById(Long customerId) {
        Customer existingCustomer = customerGatewayPort.findCustomerById(customerId);
        if (existingCustomer != null) {
            return existingCustomer;
        } else {
            throw new BussinessException("CLIENTE_NO_EXISTE", "El cliente con ID " + customerId + " no existe", "404");
        }
    }

    private Customer updateCustomerFields(Customer customerExistence, Customer modifiedCustomer) {
        customerExistence.setTipoIdentificacion(modifiedCustomer.getTipoIdentificacion());
        customerExistence.setNumeroIdentificacion(modifiedCustomer.getNumeroIdentificacion());
        customerExistence.setPrimerNombre(modifiedCustomer.getPrimerNombre());
        customerExistence.setSegundoNombre(modifiedCustomer.getSegundoNombre());
        customerExistence.setPrimerApellido(modifiedCustomer.getPrimerApellido());
        customerExistence.setSegundoApellido(modifiedCustomer.getSegundoApellido());
        customerExistence.setCorreoElectronico(modifiedCustomer.getCorreoElectronico());
        customerExistence.setFechaNacimiento(modifiedCustomer.getFechaNacimiento());
        customerExistence.setFechaModificacion(new Date());// Cambiar tipo de dato
        return customerExistence;
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerGatewayPort.findAllcustomers();
    }


    @Override
    public void deleteCustomerById(Long id) {
        Customer customerToDelete = customerGatewayPort.findCustomerById(id);

        int productCount = productGatewayPort.countProductsByClient(customerToDelete.getId());
        customerGatewayPort.deleteCustomerById(id);
    }


}
