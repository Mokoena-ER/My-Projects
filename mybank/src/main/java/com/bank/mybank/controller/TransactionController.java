package com.bank.mybank.controller;

import com.bank.mybank.dto.TransactionReqDto;
import com.bank.mybank.dto.TransactionResDto;
import com.bank.mybank.model.Transaction;
import com.bank.mybank.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transaction")
@CrossOrigin("*")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping()
    public TransactionResDto makeTransaction(@RequestBody TransactionReqDto tranReqDto) throws AccountNotFoundException {
        return transactionService.transaction(tranReqDto);
    }

    @GetMapping
    public Map<String, List<TransactionResDto>> viewTransactions() {
        return transactionService.viewAllTransactions();
    }
}
