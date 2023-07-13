package com.semicolon.grincultified.dtos.responses;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DashboardStatistic {
    private int totalNumberOfInvestments;
    private BigDecimal totalAmountInvested;
    private String upcomingPaymentDate;
}
