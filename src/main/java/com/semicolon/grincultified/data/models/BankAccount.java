package com.semicolon.grincultified.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class BankAccount {
    @Id
    private Long id;
    private String accountName;
    private String accountNumber;
    private String bankName;
}
