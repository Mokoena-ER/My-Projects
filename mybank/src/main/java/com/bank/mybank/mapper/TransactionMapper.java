package com.bank.mybank.mapper;

import com.bank.mybank.dto.TransactionReqDto;
import com.bank.mybank.dto.TransactionResDto;
import com.bank.mybank.model.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    Transaction toTranEntity(TransactionReqDto reqDto);
    TransactionResDto toTranResDto(Transaction entity);
}
