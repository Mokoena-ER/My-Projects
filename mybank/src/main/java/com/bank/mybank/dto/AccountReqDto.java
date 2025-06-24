package com.bank.mybank.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountReqDto {

    private String accountHolderName;
    private Integer age;
    private String accountType;
    private String email;
    private String phoneNumber;
    private String nationality;
    private String occupation;
}
