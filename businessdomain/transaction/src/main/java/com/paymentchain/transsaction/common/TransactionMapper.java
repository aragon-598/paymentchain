package com.paymentchain.transsaction.common;

import com.paymentchain.transsaction.dto.TransactionDto;
import com.paymentchain.transsaction.entities.Transaction;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    
    @Mappings({
        // @Mapping(source = "idTransaction", target = "idTransaction")
    })
    TransactionDto entityToDto(Transaction source);
    
    List<TransactionDto> entityListToDtoList(List<Transaction> source);
    
    @InheritInverseConfiguration
    Transaction dtoToEntity(TransactionDto source);
    
    @InheritInverseConfiguration
    List<Transaction> dtoListoToEntityList(List<TransactionDto> source);
    
}
