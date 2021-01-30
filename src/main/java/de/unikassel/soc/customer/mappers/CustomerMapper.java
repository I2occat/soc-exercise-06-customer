package de.unikassel.soc.customer.mappers;


import de.unikassel.soc.customer.domain.Customer;
import de.unikassel.soc.customer.model.CustomerDto;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDto dto);

    CustomerDto customerToCustomerDto(Customer customer);
}
