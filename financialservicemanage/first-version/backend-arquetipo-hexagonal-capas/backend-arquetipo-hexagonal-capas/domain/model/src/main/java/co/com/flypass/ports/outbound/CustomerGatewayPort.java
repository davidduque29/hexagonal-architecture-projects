package co.com.flypass.ports.outbound;

import co.com.flypass.models.Customer;

import java.util.List;

public interface CustomerGatewayPort {
    Customer createCustomer(Customer customer);
    Customer updateCustomerId(Customer customerModel);
    List<Customer> findAllcustomers();
    Customer findCustomerById(Long id);
    void deleteCustomerById(Long id);
    Customer findByNumberIdentification(String identification);

}
