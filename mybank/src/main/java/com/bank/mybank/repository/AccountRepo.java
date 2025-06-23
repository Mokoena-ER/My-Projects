package com.bank.mybank.repository;

import com.bank.mybank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
    Account findByAccountNumber(String accountNumber);
}
