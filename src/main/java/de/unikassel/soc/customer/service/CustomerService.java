package de.unikassel.soc.customer.service;


import de.unikassel.soc.customer.domain.Customer;
import de.unikassel.soc.customer.model.CustomerDto;

import java.util.UUID;

public interface CustomerService {
    CustomerDto getCustomerById(UUID customerId);

    CustomerDto saveNewCustomer(CustomerDto customerDto);

    void updateCustomer(UUID customerId, CustomerDto customerDto);

    void deleteById(UUID customerId);

    CustomerDto getCustomerByName(String customerName);

    Iterable<Customer> getAll();
}
