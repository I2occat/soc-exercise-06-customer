package de.unikassel.soc.customer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    private UUID id;

    @NotBlank
    private String productName;

    @NotBlank
    private String description;

    @Positive
    private Double price;

    @NotBlank
    private String currency;

}
