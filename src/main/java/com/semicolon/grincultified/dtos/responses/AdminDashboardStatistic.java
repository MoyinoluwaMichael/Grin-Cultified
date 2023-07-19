package com.semicolon.grincultified.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminDashboardStatistic {
    private int totalNumberOfOngoingFarmProject;
    private BigDecimal totalAmountOfMoneyInvestedByTheInvestors;
    private LocalDate nextRedeemedProject;
    private Long totalNumberOfFarmers;
    private Long totalNumberOfInvestors;
    private Long totalNumberOfSystemAdmin;
}
