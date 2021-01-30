package de.unikassel.soc.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.unikassel.soc.customer.clients.ProductClient;
import de.unikassel.soc.customer.controller.CustomerController;
import de.unikassel.soc.customer.model.CustomerDto;
import de.unikassel.soc.customer.service.CustomerService;
import de.unikassel.soc.product.controller.ProductController;
import de.unikassel.soc.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerApplicationTest {
    static final String BASE_PATH = "/api/v1/customer";

    @Mock
    CustomerService customerService;

    CustomerController customerController;
    ProductController productController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerController = new CustomerController(customerService);
    }


    @Test
    public void testDelete() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
        UUID id = UUID.randomUUID();
        CustomerDto customerDto = CustomerDto.builder()
                .id(id)
                .name("test")
                .build();
        customerService.saveNewCustomer(customerDto);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete(BASE_PATH+"/"+id)
        ).andExpect(status().isOk()).andReturn();
    }


    @Test
    void getCustomersByName() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
        String name = "Test 1";
        CustomerDto customerDto =  new CustomerDto(UUID.randomUUID(), name, null);

        when(customerService.getCustomerByName(name)).thenReturn(customerDto);

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_PATH+"/byname/"+name))
                .andExpect(status().isOk());
    }

    @Test
    void handlePost() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
        CustomerDto customerDto = new CustomerDto(UUID.randomUUID(), "Test", null);
        when(customerService.saveNewCustomer(customerDto)).thenReturn(customerDto);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/v1/customer")
                        .content(asJsonString(customerDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}