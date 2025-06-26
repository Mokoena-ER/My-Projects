package com.bank.mybank.service;

import com.bank.mybank.dto.TransactionReqDto;
import com.bank.mybank.dto.TransactionResDto;
import com.bank.mybank.mapper.TransactionMapper;
import com.bank.mybank.model.Account;
import com.bank.mybank.model.Transaction;
import com.bank.mybank.repository.AccountRepo;
import com.bank.mybank.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private final TransactionRepo transactionRepo;

    @Autowired
    private final AccountRepo accountRepo;

    @Autowired
    private final TransactionMapper mapper;

    @Autowired
    private final AccountService accountService;

    public TransactionService(TransactionRepo transactionRepo, AccountRepo accountRepo,
                              TransactionMapper mapper, AccountService accountService) {
        this.transactionRepo = transactionRepo;
        this.accountRepo = accountRepo;
        this.mapper = mapper;
        this.accountService = accountService;
    }

    //make transaction method, if transactionType = deposit/withdraw, call deposit/withdraw.
    //deposit method
    //withdraw method
    //view balance method

    public void deposit(Transaction transaction, Account account) {
        if (transaction.getAmount() > 0) {
            account.setBalance(account.getBalance() + transaction.getAmount());
        }
        else {
            throw new RuntimeException("Insufficient funds to deposit: "+transaction.getAmount());
        }
    }

    public void withdraw(Transaction transaction, Account account) {
        if (transaction.getAmount() > 0 && transaction.getAmount() <= account.getBalance()) {
            account.setBalance(account.getBalance() - transaction.getAmount());
        }
        else {
            throw new RuntimeException("Invalid funds to withdraw: "+transaction.getAmount());
        }
    }

    public TransactionResDto transaction(TransactionReqDto reqDto) throws AccountNotFoundException {

        Transaction tranEntity = mapper.toTranEntity(reqDto);
        Account existingAccount = accountRepo.findByAccountNumber(tranEntity.getAccountNumber());

        if (existingAccount != null) {
            if (tranEntity.getTransactionType().equalsIgnoreCase("Deposit")) {
                deposit(tranEntity, existingAccount);
                accountService.updateStatus(existingAccount);
            }
            else if (tranEntity.getTransactionType().equalsIgnoreCase("Withdraw")) {
                withdraw(tranEntity, existingAccount);
            }
            else {
                throw new RuntimeException("Invalid transaction, Deposit or Withdraw.");
            }
        }
        else {
            throw new AccountNotFoundException("Account Not Found");
        }

        tranEntity.setTransactionDate(LocalDateTime.now().withNano(0));

        accountRepo.save(existingAccount);
        Transaction saved = transactionRepo.save(tranEntity);

        return mapper.toTranResDto(saved);
    }

    public Map<String, List<TransactionResDto>> viewAllTransactions() {
        return transactionRepo.findAll().stream()
                .map(mapper::toTranResDto)
                .collect(Collectors.groupingBy(t -> t.getAccountNumber()));
    }



}
