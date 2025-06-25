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

import java.time.LocalDateTime;

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
            accountService.updateStatus(account);
        }else {
            throw new RuntimeException("Invalid amount!");
        }
   }

   public void withdraw(Transaction transaction, Account account) {
        if (transaction.getAmount() > 0 && transaction.getAmount() <= account.getBalance()) {
            account.setBalance(account.getBalance() - transaction.getAmount());
        }else {
            throw new RuntimeException("Invalid amount!");
        }
   }

   public TransactionResDto makeTransaction(TransactionReqDto tranReqDto) {

        Account existingAccount = accountRepo.findByAccountNumber(tranReqDto.getAccountNumber());

        Transaction entity = mapper.toTranEntity(tranReqDto);

        if (existingAccount != null && existingAccount.getAccountNumber().equals(entity.getAccountNumber())) {
            if (entity.getTransactionType().equalsIgnoreCase("Deposit")) {
                deposit(entity, existingAccount);
            } else if (entity.getTransactionType().equalsIgnoreCase("Withdraw")) {
                withdraw(entity, existingAccount);
            } else {
                throw new RuntimeException("Invalid transaction type: ' "+entity.getTransactionType()+" '.");
            }
        }else {
            throw new RuntimeException("Invalid account number: "+tranReqDto.getAccountNumber());
        }

        entity.setTransactionDate(LocalDateTime.now().withNano(0));

        Transaction saved = transactionRepo.save(entity);
        accountRepo.save(existingAccount);

        return mapper.toTranResDto(saved);
   }

}
