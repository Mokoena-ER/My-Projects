package com.bank.mybank.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String accountHolderName;
    private Integer age;
    private String accountType;
    private String email;
    private String phoneNumber;
    private String nationality;
    private String occupation;

    //branch
    private String accountNumber;
    private String branchCode;
    private String status = "Pending";
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    private double balance;
}
