package com.semicolon.grincultified.dtos.requests;

import com.semicolon.grincultified.data.models.FarmProjectStatus;
import com.semicolon.grincultified.data.models.InvestmentPlan;
import com.semicolon.grincultified.data.models.InvestmentType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
public class FarmProjectRequest {
    private String farmProduceSummary;
    private String description;
    private Long numberOfInvestors;
    private FarmProjectStatus status;
    private String pictures;
    private int roi;
    InvestmentType investmentType;
    private LocalDateTime startDate;
    private LocalDateTime maturityDate;
    private String payoutType;
}
