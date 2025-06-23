package com.bank.mybank.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountResDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String accountHolderName;
    private String accountType;
    private String email;
    private String phoneNumber;
    private String nationality;
    private String occupation;

    //branch
    private String accountNumber;
    private String branchCode;
    private String status;
    private LocalDateTime dateCreated;
}
