package com.bank.mybank.controller;

import com.bank.mybank.dto.TransactionReqDto;
import com.bank.mybank.dto.TransactionResDto;
import com.bank.mybank.service.TransactionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction")
@CrossOrigin("*")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping()
    public TransactionResDto makeTransaction(@RequestBody TransactionReqDto tranReqDto) {
        return transactionService.makeTransaction(tranReqDto);
    }
}
