package com.semicolon.grincultified.dtos.responses;

import com.semicolon.grincultified.data.models.FarmProjectStatus;
import com.semicolon.grincultified.data.models.InvestmentPlan;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class FarmProjectResponse {
    private String farmProduceSummary;
    private String description;
    private Long numberOfInvestors;
    private FarmProjectStatus status;
    private String pictures;
    private LocalDateTime uploadedAt;
}
