package com.semicolon.grincultified.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class FarmProject {
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
