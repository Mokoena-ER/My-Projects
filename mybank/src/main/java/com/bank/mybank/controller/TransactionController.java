package com.bank.mybank.controller;

import com.bank.mybank.dto.TransactionReqDto;
import com.bank.mybank.dto.TransactionResDto;
import com.bank.mybank.model.Transaction;
import com.bank.mybank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transaction")
@CrossOrigin("*")
public class TransactionController {

    @Autowired
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping()
    public ResponseEntity<TransactionResDto> makeTransaction(@RequestBody TransactionReqDto tranReqDto) throws AccountNotFoundException {
        return ResponseEntity.ok(transactionService.transaction(tranReqDto));
    }

    @GetMapping("/view")
    public ResponseEntity<List<TransactionResDto>> viewTransactions() {
        return ResponseEntity.ok(transactionService.viewAllTransactions());
    }
}
