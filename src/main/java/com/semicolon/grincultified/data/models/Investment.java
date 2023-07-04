package com.semicolon.grincultified.data.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Investment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long investorId;
    @OneToOne
    private FarmProject farmProject;
    private BigDecimal amount;
    private InvestmentReturnType returnType;
    private InvestmentStatus status;
    private LocalDateTime createdAt;
    private RedemptionStatus redemptionStatus;
}
