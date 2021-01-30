package de.unikassel.soc.client.web.client;

import de.unikassel.soc.customer.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("socproduct")
public interface ProductClient {

    @GetMapping("/api/v1/product/products")
    Iterable<Product> getAllProducts();
}
