package com.bank.mybank.controller;

import com.bank.mybank.dto.AccountReqDto;
import com.bank.mybank.dto.AccountResDto;
import com.bank.mybank.model.Account;
import com.bank.mybank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/account")
@CrossOrigin("*")
public class AccountController {

    @Autowired
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public AccountResDto createAccount(@RequestBody AccountReqDto reqDto) {
        return accountService.createAccount(reqDto);
    }

    @GetMapping
    public Map<String, List<AccountResDto>> viewAccount() {
        return accountService.viewAccount();
    }

    @PutMapping("/update")
    public AccountResDto updateAccount(@RequestBody Account entity,
                                       @RequestBody AccountReqDto reqDto)
                                        throws AccountNotFoundException
    {
        return accountService.updateAccount(entity, reqDto);
    }

}
