package com.semicolon.grincultified.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    private Long farmProjectId;
    private BigDecimal amount;
    private InvestmentReturnType returnType;
    private InvestmentStatus status;
    private LocalDateTime createdAt;
    private RedemptionStatus redemptionStatus;
}
