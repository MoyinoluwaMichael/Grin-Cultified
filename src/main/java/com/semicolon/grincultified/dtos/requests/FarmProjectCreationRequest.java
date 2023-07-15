package com.semicolon.grincultified.dtos.requests;

import com.semicolon.grincultified.data.models.FarmProjectStatus;
import com.semicolon.grincultified.data.models.InvestmentType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Setter
@Getter
public class FarmProjectCreationRequest {
    private String farmProduceSummary;
    private String description;
    private String picture;
    private String location;
    private int roi;
    private InvestmentType investmentType;
    private LocalDateTime startDate;
    private LocalDateTime maturityDate;
    private String payoutType;
    private BigDecimal pricePerUnit;
}
