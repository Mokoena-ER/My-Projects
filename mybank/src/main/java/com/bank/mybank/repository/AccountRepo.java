package com.bank.mybank.repository;

import com.bank.mybank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
    Account findByAccountNumber(String accountNumber);
    //Use Optional<> here to use .orElseThrow() in service when using this function.
}
