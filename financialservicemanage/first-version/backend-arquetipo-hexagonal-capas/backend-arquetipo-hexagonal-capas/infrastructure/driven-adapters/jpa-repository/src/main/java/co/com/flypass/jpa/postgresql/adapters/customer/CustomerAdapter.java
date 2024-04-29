package co.com.flypass.jpa.postgresql.adapters.customer;

import co.com.flypass.jpa.postgresql.adapters.customer.entity.CustomerEntity;
import co.com.flypass.jpa.postgresql.adapters.customer.mapper.CustomerConverter;
import co.com.flypass.jpa.postgresql.adapters.customer.repository.CustomerCrudRepository;
import co.com.flypass.models.Customer;
import co.com.flypass.jpa.postgresql.adapters.customer.mapper.impl.ICustomerMapper;
import co.com.flypass.ports.outbound.CustomerGatewayPort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerAdapter implements CustomerGatewayPort {
    private final CustomerCrudRepository customerCrudRepository;
    private final ICustomerMapper iCustomerMapper;

    public CustomerAdapter(CustomerCrudRepository customerCrudRepository, ICustomerMapper iCustomerMapper) {
        this.customerCrudRepository = customerCrudRepository;
        this.iCustomerMapper = iCustomerMapper;
    }

    @Override
    public Customer createCustomer(Customer customerModel) {
        CustomerConverter customerConverter = new CustomerConverter();
        return iCustomerMapper.convertEntityToCostumer(customerCrudRepository
                .save(customerConverter.convertCustomerToEntity(customerModel)));
    }

    @Override
    public Customer updateCustomerId(Customer customerModel) {
        CustomerConverter customerConverter = new CustomerConverter();
        return iCustomerMapper.convertEntityToCostumer(customerCrudRepository
                .save(customerConverter.convertCustomerToEntity(customerModel)));
    }

    @Override
    public List<Customer> findAllcustomers() {
        return iCustomerMapper.convertListEntityToCustomerList(customerCrudRepository.findAll());
    }

    @Override
    public Customer findCustomerById(Long id) {
        CustomerEntity customerEntity = customerCrudRepository.findById(id).orElse(null);
        return iCustomerMapper.convertEntityToCostumer(customerEntity);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerCrudRepository.deleteById(id);
    }

    @Override
    public Customer findByNumberIdentification(String identification) {
        return iCustomerMapper.convertEntityToCostumer(customerCrudRepository
                .findByNumeroIdentificacion(identification));

    }
}
