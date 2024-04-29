package co.com.flypass.ports.inbound;

import co.com.flypass.models.Customer;

import java.util.List;

public interface CustomerUseCasePort {
    Customer createCustomer(Customer customer);
    Customer updateCustomerId(Customer customerInput);
    List<Customer> findAllCustomers();
    void deleteCustomerById(Long id);
}

