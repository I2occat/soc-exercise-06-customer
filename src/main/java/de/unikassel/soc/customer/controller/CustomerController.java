package de.unikassel.soc.customer.controller;

import com.netflix.discovery.converters.Auto;
import de.unikassel.soc.client.web.client.ProductClient;
import de.unikassel.soc.customer.domain.Customer;
import de.unikassel.soc.customer.mappers.CustomerMapper;
import de.unikassel.soc.customer.mappers.CustomerMapperImpl;
import de.unikassel.soc.customer.model.CustomerDto;
import de.unikassel.soc.customer.repos.CustomerRepo;
import de.unikassel.soc.customer.service.CustomerService;
import de.unikassel.soc.customer.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/customer", produces = "application/json")
@CrossOrigin(origins = "*")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    private ProductClient productClient;

    @GetMapping("/customers")
    public Iterable<Customer> allCustomers() {
        return customerService.getAll();
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("customerId") UUID customerId){
        return new ResponseEntity<>(customerService.getCustomerById((customerId)), HttpStatus.OK);
    }

    @GetMapping("/byname/{customerName}")
    public ResponseEntity<CustomerDto> getCustomerByName(@PathVariable("customerName")  String customerName){

        return new ResponseEntity<>(customerService.getCustomerByName(customerName), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity handlePost(@RequestBody @Validated CustomerDto customerDto){
        CustomerDto savedDto = customerService.saveNewCustomer(customerDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/v1/customer/" + savedDto.getId().toString());

        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }

    @PostMapping("/createrandom")
    public ResponseEntity handlePost(){
        CustomerDto saved = new CustomerDto();
        saved.setName("Test");
        saved.setId(UUID.randomUUID());
        customerService.saveNewCustomer(saved);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/v1/customer/" + saved.getId().toString());

        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleUpdate(@PathVariable("customerId") UUID customerId, @Validated @RequestBody CustomerDto customerDto){
        customerService.updateCustomer(customerId, customerDto);
    }

    @DeleteMapping("/{customerId}")
    public void deleteById(@PathVariable("customerId")  UUID customerId){
        customerService.deleteById(customerId);
    }
}
