package com.bank.mybank.service;

import com.bank.mybank.dto.AccountReqDto;
import com.bank.mybank.dto.AccountResDto;
import com.bank.mybank.mapper.AccountMapper;
import com.bank.mybank.model.Account;
import com.bank.mybank.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private final AccountRepo accountRepo;

    @Autowired
    private AccountMapper mapper;

    public AccountService(AccountRepo accountRepo, AccountMapper mapper) {
        this.accountRepo = accountRepo;
        this.mapper = mapper;
    }


    public AccountResDto createAccount(AccountReqDto reqDto) {
        Account entity = mapper.toEntity(reqDto);
        Account saved = accountRepo.save(entity);
        return mapper.toResDto(saved);
    }

}
