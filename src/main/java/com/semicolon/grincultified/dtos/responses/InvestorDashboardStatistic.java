package com.semicolon.grincultified.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvestorDashboardStatistic {
    private int totalNumberOfInvestments;
    private BigDecimal totalAmountInvested;
    private String upcomingPaymentDate;

    @Override
    public String toString() {
        return "InvestorDashboardStatistic{" +
                "totalNumberOfInvestments=" + totalNumberOfInvestments +
                ", totalAmountInvested=" + totalAmountInvested +
                ", upcomingPaymentDate='" + upcomingPaymentDate + '\'' +
                '}';
    }
}
