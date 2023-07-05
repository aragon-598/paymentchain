package com.paymentchain.customer.common;


import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.paymentchain.customer.dto.CustomerDto;
import com.paymentchain.customer.entities.Customer;

@Mapper(componentModel = "spring")
public interface CustomerResponseMapper {

    @Mappings(value = {@Mapping(source = "iban",target = "iban"),
                        @Mapping(source = "customerId",target = "customerId")
                        })
    CustomerDto entityToDto(Customer source);

    List<CustomerDto> entityListToDtoList(List<Customer> source);

    @InheritInverseConfiguration
    Customer dtoToEntity(CustomerDto source);

    @InheritInverseConfiguration
    List<Customer> dtoListToEntityList(List<CustomerDto> source);
}
