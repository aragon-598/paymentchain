package com.paymentchain.customer.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paymentchain.customer.entities.Customer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CustomerProductDto {
    @Schema(name = "id",required = false,example = "1",defaultValue = "2",description = "This key is autogenerated, it's not necesary")
    private long id;
    @Schema(name = "productId",required = false,example = "1",defaultValue = "2",description = "Id para los productos del cliente")
    private long productId;
    @Schema(name = "id",required = false,example = "Product name",defaultValue = "Product name",description = "Nombre del producto")
    private String productName;
    @JsonIgnore
    private Customer customer;
}
