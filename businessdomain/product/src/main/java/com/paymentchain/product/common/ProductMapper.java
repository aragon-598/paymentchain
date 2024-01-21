/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.paymentchain.product.common;

import com.paymentchain.product.dto.ProductDto;
import com.paymentchain.product.entities.Product;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 *
 * @author aragon
 */
@Mapper(componentModel = "spring")
public interface ProductMapper {
    
    @Mappings({
        @Mapping(source = "id",target = "id"),
    })
    ProductDto entityToDto(Product source);

    List<ProductDto> entityListToDtoList(List<Product> source);

    @InheritInverseConfiguration
    Product dtoToEntity(ProductDto source);

    @InheritInverseConfiguration
    List<Product> dtoListToEntityList(List<ProductDto> source);
}
