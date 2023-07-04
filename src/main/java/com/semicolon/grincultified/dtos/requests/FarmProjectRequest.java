package com.semicolon.grincultified.dtos.requests;

import com.semicolon.grincultified.data.models.FarmProjectStatus;
import com.semicolon.grincultified.data.models.InvestmentPlan;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
public class FarmProjectRequest {
    @Id
    private Long id;
    private String farmProduceSummary;
    private String description;
    private Long numberOfInvestors;
    private FarmProjectStatus status;
    private String pictures;
    @OneToOne
    private InvestmentPlan investmentPlan;
    private LocalDateTime uploadedAt;
}
