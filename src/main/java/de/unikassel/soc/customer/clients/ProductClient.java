package de.unikassel.soc.customer.clients;

import de.unikassel.soc.customer.model.ProductDto;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("socproduct")
public interface ProductClient {

    @GetMapping("/api/v1/product/products")
    Iterable<ProductDto> getAllProducts();

//    @RequestLine("GET /api/v1/product/byCustomer/{customerId}")
    @GetMapping("/api/v1/product/byCustomer/{customerId}")
    List<ProductDto> getProductsByCustomerId(@PathVariable("customerId") String customerId);
}
