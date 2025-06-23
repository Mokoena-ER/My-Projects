package com.bank.mybank.service;

import com.bank.mybank.dto.AccountReqDto;
import com.bank.mybank.dto.AccountResDto;
import com.bank.mybank.mapper.AccountMapper;
import com.bank.mybank.model.Account;
import com.bank.mybank.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

        entity.setAccountNumber(String.valueOf(UUID.randomUUID()));
        entity.setDateCreated(LocalDateTime.now().withNano(0));
        entity.setBranchCode("470010");

        Account saved = accountRepo.save(entity);
        return mapper.toResDto(saved);
    }

    public List<AccountResDto> viewAccount() {
        return accountRepo.findAll().stream()
                .map(mapper::toResDto)
                .collect(Collectors.toList());
    }
}
