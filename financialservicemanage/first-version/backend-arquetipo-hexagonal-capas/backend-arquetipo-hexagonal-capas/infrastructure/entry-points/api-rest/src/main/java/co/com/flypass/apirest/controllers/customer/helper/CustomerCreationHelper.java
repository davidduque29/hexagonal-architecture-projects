package co.com.flypass.apirest.controllers.customer.helper;

import co.com.flypass.apirest.controllers.controllerconstants.OperationConstants;
import co.com.flypass.apirest.controllers.controllermessage.StateMessage;
import co.com.flypass.apirest.dtos.request.customer.CustomerRequestDTO;
import co.com.flypass.apirest.controllers.customer.response.CustomerResponse;
import co.com.flypass.exception.BussinessException;
import co.com.flypass.models.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerCreationHelper {
    private Customer customer;
    private StateMessage stateMessage;
    private CustomerResponse customerResponse;


    public Customer convertRequestToCustomer(CustomerRequestDTO request) {
        if (request == null){
            return null;
        }
        customer = Customer.builder()
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
        customer.validCustomer();
        return customer;
    }


    public StateMessage getStateMessages(BussinessException exc) {
        return StateMessage.builder()
                .status(exc.getStatus())
                .detail(exc.getDetail())
                .statusCode(Integer.parseInt(exc.getCode()))
                .build();
    }


    //crear el custumerResponse de exito que va a devolver en el try
    public CustomerResponse createResponse(Customer customer) {
        customerResponse = new CustomerResponse<>();
        stateMessage = StateMessage.builder()
                .statusCode(200)
                .status(OperationConstants.STATUS_OK)
                .title(OperationConstants.TITLE_OK)
                .detail(OperationConstants.DETAIL_OK)
                .build();
        customerResponse.setClient(customer);
        customerResponse.setStateMessage(stateMessage);
        return customerResponse;
    }

    public CustomerResponse createResponse(List<Customer> listCustomer) {
        customerResponse = new CustomerResponse<>();
        stateMessage = StateMessage.builder()
                .statusCode(200)
                .status(OperationConstants.STATUS_OK)
                .title(OperationConstants.TITLE_OK)
                .detail(OperationConstants.DETAIL_OK)
                .build();
        customerResponse.setClient(listCustomer);
        customerResponse.setStateMessage(stateMessage);
        return customerResponse;
    }

    public CustomerResponse createDeletedUserResponse() {
        customerResponse = new CustomerResponse<>();
        stateMessage = StateMessage.builder()
                .statusCode(200)
                .status(OperationConstants.STATUS_OK)
                .title(OperationConstants.TITLE_OK)
                .detail(OperationConstants.STATUS_DETAIL_DELETE)
                .build();
        customerResponse.setClient("{}");
        customerResponse.setStateMessage(stateMessage);
        return customerResponse;
    }

    public CustomerResponse createDeletedUserResponse(Customer customer) {
        customerResponse = new CustomerResponse<>();
        stateMessage = StateMessage.builder()
                .statusCode(200)
                .status(OperationConstants.STATUS_OK)
                .title(OperationConstants.TITLE_OK)
                .detail(OperationConstants.STATUS_DETAIL_DELETE)
                .build();
        customerResponse.setClient(customer);
        customerResponse.setStateMessage(stateMessage);
        return customerResponse;
    }
}
