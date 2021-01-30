package de.unikassel.soc.customer.repos;


import de.unikassel.soc.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CustomerRepo extends JpaRepository<Customer, UUID> {
    List<Customer> findByName(String name);
}
