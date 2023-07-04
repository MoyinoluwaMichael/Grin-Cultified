package com.semicolon.grincultified.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class InvestmentPlan {
    @Id
    private Long id;
    private int roi;
    InvestmentType investmentType;
    private LocalDateTime startDate;
    private LocalDateTime maturityDate;
    private String payoutType;
}
