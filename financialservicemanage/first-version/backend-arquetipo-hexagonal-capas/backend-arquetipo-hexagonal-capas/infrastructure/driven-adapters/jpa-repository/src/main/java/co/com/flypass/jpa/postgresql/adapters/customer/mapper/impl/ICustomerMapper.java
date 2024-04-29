package co.com.flypass.jpa.postgresql.adapters.customer.mapper.impl;


import co.com.flypass.jpa.postgresql.adapters.customer.entity.CustomerEntity;
import co.com.flypass.models.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerMapper {
    CustomerEntity convertCustomerToEntityBuilder(Customer customer);
    CustomerEntity convertCustomerToEntity(Customer customerDto);
    CustomerEntity convertCustomerToEntityOptional(Optional<Customer> customerDto);
    Customer convertEntityToCostumer(CustomerEntity entity);
    Customer convertEntityToCostumerOptional(Optional<CustomerEntity> entity);
    List<CustomerEntity> convertToEntityList(List<Customer> customerDtos);
    List<Customer> convertListEntityToCustomerList(List<CustomerEntity> entities);
    List<Optional<CustomerEntity>> convertToEntityOptionalList(List<Optional<Customer>> optionalcustomerDtos);
    List<Optional<Customer>> convertTocustomerDtoOptionalList(List<Optional<CustomerEntity>> optionalEntities);
}
