package co.com.flypass.apirest.controllers.customer;


import co.com.flypass.apirest.controllers.customer.handlers.ICustomerHandler;
import co.com.flypass.apirest.controllers.customer.helper.CustomerCreationHelper;
import co.com.flypass.apirest.dtos.request.customer.CustomerRequestDTO;
import co.com.flypass.apirest.controllers.customer.response.CustomerResponse;
import co.com.flypass.apirest.dtos.response.CustomerResponseDTO;
import co.com.flypass.exception.BussinessException;
import co.com.flypass.models.Customer;
import co.com.flypass.ports.inbound.CustomerUseCasePort;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.com.flypass.apirest.controllers.customer.headers.CustomerApiHeadersBuilder;

import java.util.List;


@Tag(name = "Customer API", description = "Endpoints para gestionar clientes")
@CrossOrigin
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CustomerApi {
    private final CustomerUseCasePort customerUseCasePort;
    private final CustomerCreationHelper creationHelper;
    private CustomerResponse<Customer> customerResponse;
    private final ICustomerHandler customerHandler;


    @PostMapping("/createcustomer")
    public ResponseEntity<CustomerResponseDTO> addCustomerInformation(@RequestBody CustomerRequestDTO customerRequestDTO) {

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(customerHandler.add(customerRequestDTO));
    }

    @PutMapping("/updatecostumer")
    public ResponseEntity<CustomerResponse<Customer>> updateCustomerRegisterId(@RequestBody CustomerRequestDTO customerInput) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .headers(CustomerApiHeadersBuilder.buildHeaders())
                    .body(customerHandler.updateCustomerRegisterId(customerInput));
        } catch (BussinessException exc) {
            customerResponse = new CustomerResponse<>();
            customerResponse.setStateMessage(creationHelper.getStateMessages(exc));
            return new ResponseEntity<>(customerResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deletecustomer/{id}")
    public ResponseEntity<CustomerResponse<Customer>> deleteCustomer(@PathVariable("id") Long id) {
        try {
            customerHandler.deleteClientById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BussinessException exc) {
            customerResponse = new CustomerResponse<>();
            customerResponse.setStateMessage(creationHelper.getStateMessages(exc));
            return new ResponseEntity<>(customerResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listcustomers")
    public ResponseEntity<CustomerResponse<Customer>> listCustomers() {
        try {
            List<Customer> customersList = customerUseCasePort.findAllCustomers();
            customerResponse = creationHelper.createResponse(customersList);
            return new ResponseEntity<>(customerResponse, HttpStatus.OK);
        } catch (BussinessException exc) {
            customerResponse = new CustomerResponse<>();
            customerResponse.setStateMessage(creationHelper.getStateMessages(exc));
            return new ResponseEntity<>(customerResponse, HttpStatus.BAD_REQUEST);
        }
    }
}