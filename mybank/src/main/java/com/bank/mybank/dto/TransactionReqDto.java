package com.bank.mybank.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionReqDto {

    private String accountNumber;
    private String transactionType;
    private double amount;

}
