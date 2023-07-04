package com.semicolon.grincultified.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Investment {
    @Id
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
