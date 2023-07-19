package com.semicolon.grincultified.dtos.responses;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ProjectInvestmentsStatistics {
    private String farmProjectName;
    private Long numberOfInvestors;
    private BigDecimal totalAmountInvested;
    private int roi;
    private BigDecimal totalExpectedAmount;
    private LocalDate maturityDate;

}
