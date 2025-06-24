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
import java.util.Map;
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

    public void setBranchCode(Account entity) {

        if (entity.getNationality().equalsIgnoreCase("RSA")) {
            entity.setBranchCode("470010");
        } else {
            entity.setBranchCode("470010-"+entity.getNationality().toUpperCase());
        }

        if (entity.getBalance() > 0) {
            entity.setStatus("Active");
        }else {
            entity.setStatus("Pending");
        }
    }

    public AccountResDto createAccount(AccountReqDto reqDto) {
        Account entity = mapper.toEntity(reqDto);

        entity.setAccountNumber(String.valueOf(UUID.randomUUID()));
        entity.setDateCreated(LocalDateTime.now().withNano(0));
        setBranchCode(entity);

        Account saved = accountRepo.save(entity);
        return mapper.toResDto(saved);
    }

    public AccountResDto updateAccount(Account entity, AccountReqDto reqDto) {
        Account existingAccount = accountRepo.findByAccountNumber(entity.getAccountNumber());

        if (reqDto.getAccountHolderName() != null && !reqDto.getAccountHolderName().isEmpty()) {
            existingAccount.setAccountHolderName(reqDto.getAccountHolderName());
        }else {
            existingAccount.setAccountHolderName(existingAccount.getAccountHolderName());
        }
        if ((reqDto.getAccountType() != null && !reqDto.getAccountType().isEmpty())){
            existingAccount.setAccountType(reqDto.getAccountType());
        }else {
            existingAccount.setAccountType(existingAccount.getAccountType());
        }
        if (reqDto.getEmail() != null && !reqDto.getEmail().isEmpty()) {
            existingAccount.setEmail(reqDto.getEmail());
        }else {
            existingAccount.setEmail(existingAccount.getEmail());
        }
        if (reqDto.getPhoneNumber() != null && !reqDto.getPhoneNumber().isEmpty()) {
            existingAccount.setPhoneNumber(reqDto.getPhoneNumber());
        }else {
            existingAccount.setPhoneNumber(existingAccount.getPhoneNumber());
        }
        if (reqDto.getNationality() != null && !reqDto.getNationality().isEmpty()) {
            existingAccount.setNationality(reqDto.getNationality());
        }else {
            existingAccount.setNationality(existingAccount.getNationality());
        }
        if (reqDto.getOccupation() != null && !reqDto.getOccupation().isEmpty()) {
            existingAccount.setOccupation(reqDto.getOccupation());
        }else {
            existingAccount.setOccupation(existingAccount.getOccupation());
        }
        if (reqDto.getAge() != null) {
            existingAccount.setAge(reqDto.getAge());
        }else {
            existingAccount.setAge(existingAccount.getAge());
        }

        existingAccount.setDateUpdated(LocalDateTime.now().withNano(0));
        setBranchCode(existingAccount);

        Account updated = accountRepo.save(existingAccount);

        return mapper.toResDto(updated);
    }

    public Map<String, List<AccountResDto>> viewAccount() {
        return accountRepo.findAll().stream()
                .map(mapper::toResDto)
                .collect(Collectors.groupingBy(a -> a.getOccupation()));
    }

}
