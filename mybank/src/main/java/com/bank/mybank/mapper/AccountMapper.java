package com.bank.mybank.mapper;

import com.bank.mybank.dto.AccountReqDto;
import com.bank.mybank.dto.AccountResDto;
import com.bank.mybank.model.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toEntity(AccountReqDto reqDto);
    AccountResDto toResDto(Account entity);
}
